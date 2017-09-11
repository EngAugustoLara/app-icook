//package cerprosoft.com.br.forquimica.dao;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Environment;
//import android.util.Log;
//
//import com.cerpro.dao.PadraoDAO;
//import com.cerpro.utils.Constantes;
//import com.cerpro.utils.DataCalendario;
//import com.cerpro.utils.Formats;
//import com.cerpro.utils.Logs;
//import com.cerpro.utils.ManipulacaoArquivoTexto;
//import com.cerpro.utils.ManipulacaoDiretorios;
//import com.cerpro.utils.ManipulacaoString;
//
//import org.apache.commons.lang3.BooleanUtils;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.Locale;
//
//import cerprosoft.com.br.forquimica.database.AtributosBancoDados;
//import cerprosoft.com.br.forquimica.database.DbHelper;
//import cerprosoft.com.br.forquimica.enums.StatusAgenda;
//import cerprosoft.com.br.forquimica.interfaces.IDao;
//import cerprosoft.com.br.forquimica.model.Agenda;
//import cerprosoft.com.br.forquimica.model.Parametro;
//import cerprosoft.com.br.forquimica.util.Sessao;
//
///**
// * Created by paulo on 10/04/17.
// */
//
//public class AgendaDao extends PadraoDAO implements IDao<Agenda> {
//    private SQLiteDatabase mDbLocal = null;
//
//    public AgendaDao(Context ctx, SQLiteDatabase db) {
//        super(ctx, AtributosBancoDados.Agenda.NOME_TABELA);
//
//        mDbLocal = db;
//    }
//
//    @Override
//    public boolean inserir(Agenda obj) {
//        return super.insert(getContentValues(obj), DbHelper.getBancoEscrita(mContext, mDbLocal), mDbLocal == null);
//    }
//
//    @Override
//    public boolean alterar(Agenda obj) {
//        return super.update(getContentValues(obj), AtributosBancoDados.Agenda.ID_AGENDA + "=?",
//                new String[]{obj.getIdAgenda()}, DbHelper.getBancoEscrita(mContext, mDbLocal),
//                mDbLocal == null);
//    }
//
//    public void alterar(List<Agenda> agendas) {
//        SQLiteDatabase db = DbHelper.getBancoEscrita(mContext, mDbLocal);
//        try {
//            if (agendas != null) {
//                for (Agenda agenda : agendas) {
//                    alterar(agenda);
//                }
//            }
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), e.getMessage());
//        } finally {
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//    }
//
//    @Override
//    public boolean existe() {
//        return false;
//    }
//
//    @Override
//    public boolean existe(Agenda obj) {
//        return super.exists(new String[]{AtributosBancoDados.Agenda.ID_AGENDA}, new String[]{obj.getIdAgenda().toString()},
//                DbHelper.getBancoEscrita(mContext, mDbLocal), mDbLocal == null);
//    }
//
//    @Override
//    public boolean apagar(Agenda obj) {
//        return super.delete(AtributosBancoDados.Agenda.ID_AGENDA + "=?", new String[]{obj.getIdAgenda().toString()},
//                DbHelper.getBancoEscrita(mContext, mDbLocal), mDbLocal == null);
//    }
//
//    @Override
//    public boolean apagarTodos() {
//        return super.deleteAll(DbHelper.getBancoEscrita(mContext, mDbLocal),
//                mDbLocal == null);
//    }
//
//    public boolean apagarEnviados() {
//        boolean retorno = false;
//        SQLiteDatabase db = DbHelper.getBancoEscrita(mContext, mDbLocal);
//
//        //Padrão 15 dias, depois vamos parametrizar.
//        Date dataLimite = new Date();
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.setTime(dataLimite);
//        gc.set(Calendar.DATE, gc.get(Calendar.DATE) - 15);
//        dataLimite = gc.getTime();
//
//        try {
//            retorno = db.delete(mTable, AtributosBancoDados.Agenda.ENVIADO + " = ? AND ("
//                    + AtributosBancoDados.Agenda.DATA_ENVIO + " IS NULL OR " +  AtributosBancoDados.Agenda.DATA_ENVIO + " < ? )", new String[]{"1", Formats.DATA_HORA_DB.format(dataLimite)}) > 0;
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), e.getMessage());
//        } finally {
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//        return retorno;
//    }
//
//    @Override
//    public Agenda buscar() {
//        return null;
//    }
//
//    @Override
//    public Agenda buscar(Agenda obj) {
//        if (obj != null && obj.getIdAgenda() != null) {
//            return buscar(obj.getIdAgenda());
//        }
//        return null;
//    }
//
//    public Agenda buscar(String id) {
//        SQLiteDatabase db = DbHelper.getBancoLeitura(mContext, mDbLocal);
//        Agenda retorno = null;
//        Cursor c = null;
//        try {
//            if (id != null) {
//                c = db.query(mTable, null, AtributosBancoDados.Agenda.ID_AGENDA + "=?", new String[]{id}, null, null, null);
//                if (c.moveToFirst()) {
//                    Agenda agenda = getObjetoPreenchido(c);
//                    buscarObjetosRelacionados(agenda, db, true);
//                    retorno = agenda;
//                }
//            }
//        } catch (Exception e) {
//            Logs.mostrarLog(e);
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//        return retorno;
//    }
//
//    @Override
//    public Agenda buscar(Long id) {
//        SQLiteDatabase db = DbHelper.getBancoLeitura(mContext, mDbLocal);
//        Agenda retorno = null;
//        Cursor c = null;
//        try {
//            if (id != null) {
//                c = db.query(mTable, null, AtributosBancoDados.Agenda.ID_AGENDA + "=?", new String[]{id.toString()}, null, null, null);
//                if (c.moveToFirst()) {
//                    Agenda agenda = getObjetoPreenchido(c);
//                    buscarObjetosRelacionados(agenda, db, true);
//                    retorno = agenda;
//                }
//            }
//        } catch (Exception e) {
//            Logs.mostrarLog(e);
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//        return retorno;
//    }
//
//    @Override
//    public List<Agenda> buscarTodos() {
//        return buscarLista();
//    }
//
//    @Override
//    public List<Agenda> buscarTodos(Agenda obj) {
//        return buscarLista();
//    }
//
//    @Override
//    public List<Agenda> buscarTodos(Long id) {
//        return buscarLista();
//    }
//
//    @Override
//    public ContentValues getContentValues(Agenda obj) {
//        ContentValues retorno = new ContentValues();
//        try {
//            if (obj.getIdAgenda() != null)
//                retorno.put(AtributosBancoDados.Agenda.ID_AGENDA, obj.getIdAgenda().toString());
//            if (obj.getidAgendaOrigem() != null)
//                retorno.put(AtributosBancoDados.Agenda.AGENDA_ORIGEM, obj.getidAgendaOrigem().toString());
//            if (obj.getDataAgenda() != null)
//                retorno.put(AtributosBancoDados.Agenda.DATA_AGENDA, Formats.DATA_HORA_DB.format(obj.getDataAgenda()));
//            if (obj.getDataCadastro() != null)
//                retorno.put(AtributosBancoDados.Agenda.DATA_CADASTRO, Formats.DATA_HORA_DB.format(obj.getDataCadastro()));
//            if (obj.getDataConclusao() != null)
//                retorno.put(AtributosBancoDados.Agenda.DATA_CONCLUSAO, Formats.DATA_HORA_DB.format(obj.getDataConclusao()));
//            if (obj.getIdAgendaERP() != null)
//                retorno.put(AtributosBancoDados.Agenda.ID_AGENDA_ERP, obj.getIdAgendaERP());
//            if (obj.getDescricaoAgenda() != null)
//                retorno.put(AtributosBancoDados.Agenda.DESCRICAO_AGENDA, obj.getDescricaoAgenda());
//            if (obj.getDescricaoConclusao() != null)
//                retorno.put(AtributosBancoDados.Agenda.DESCRICAO_CONCLUSAO, obj.getDescricaoConclusao());
//            if (obj.getCliente() != null)
//                retorno.put(AtributosBancoDados.Agenda.ID_CLIENTE, obj.getCliente().getIdCliente());
//            if (obj.getSetorComercial().getIdSetorComercial() != null)
//                retorno.put(AtributosBancoDados.Agenda.ID_SETOR_COMERCIAL, obj.getSetorComercial().getIdSetorComercial());
//            if (obj.getQuantidadeAdiamentos() != null)
//                retorno.put(AtributosBancoDados.Agenda.QUANTIDADE_ADIAMENTOS, obj.getQuantidadeAdiamentos());
//            if (obj.getStatus() != null)
//                retorno.put(AtributosBancoDados.Agenda.STATUS, obj.getStatus());
//            if (obj.getStatusTransmissao() != null)
//                retorno.put(AtributosBancoDados.Agenda.STATUS_TRANSMISSAO, obj.getStatusTransmissao());
//            if (obj.getTipoAgenda().getIdTipoAgenda() != null)
//                retorno.put(AtributosBancoDados.Agenda.TIPO_AGENDA, obj.getTipoAgenda().getIdTipoAgenda());
//            if (obj.getTipoConclusao() != null && obj.getTipoConclusao().getIdTipoConclusao() != null)
//                retorno.put(AtributosBancoDados.Agenda.TIPO_CONCLUSAO, obj.getTipoConclusao().getIdTipoConclusao());
//            if (obj.getTituloAgenda() != null)
//                retorno.put(AtributosBancoDados.Agenda.TITULO_AGENDA, obj.getTituloAgenda());
//            if (obj.getUsuarioCadastro() != null)
//                retorno.put(AtributosBancoDados.Agenda.USUARIO_CADASTRO, obj.getUsuarioCadastro());
//            if (obj.getOrigemCadastro() != null)
//                retorno.put(AtributosBancoDados.Agenda.ORIGEM_CADASTRO, obj.getOrigemCadastro());
//            if (obj.getEnviado() != null)
//                retorno.put(AtributosBancoDados.Agenda.ENVIADO, BooleanUtils.toInteger(obj.getEnviado()));
//            if (obj.getDataEnvio() != null)
//                retorno.put(AtributosBancoDados.Agenda.DATA_ENVIO, Formats.DATA_HORA_DB.format(obj.getDataEnvio()));
//        } catch (Exception e) {
//            Logs.mostrarLog(e);
//        }
//        return retorno;
//    }
//
//    @Override
//    public Agenda getObjetoPreenchido(Cursor c) {
//        Agenda retorno = new Agenda();
//        try {
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.ID_AGENDA))))
//                retorno.setIdAgenda(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.ID_AGENDA)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.AGENDA_ORIGEM))))
//                retorno.setIdAgendaOrigem(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.AGENDA_ORIGEM)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.DATA_AGENDA))))
//                retorno.setDataAgenda(Formats.DATA_HORA_DB.parse(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.DATA_AGENDA))));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.DATA_CADASTRO))))
//                retorno.setDataCadastro(Formats.DATA_HORA_DB.parse(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.DATA_CADASTRO))));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.DATA_CONCLUSAO))))
//                retorno.setDataConclusao(Formats.DATA_HORA_DB.parse(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.DATA_CONCLUSAO))));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.ID_AGENDA_ERP))))
//                retorno.setIdAgendaERP(c.getLong(c.getColumnIndex(AtributosBancoDados.Agenda.ID_AGENDA_ERP)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.DESCRICAO_AGENDA))))
//                retorno.setDescricaoAgenda(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.DESCRICAO_AGENDA)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.DESCRICAO_CONCLUSAO))))
//                retorno.setDescricaoConclusao(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.DESCRICAO_CONCLUSAO)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.ID_CLIENTE))))
//                retorno.getCliente().setIdCliente(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.ID_CLIENTE)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.ID_SETOR_COMERCIAL))))
//                retorno.getSetorComercial().setIdSetorComercial(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.ID_SETOR_COMERCIAL)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.QUANTIDADE_ADIAMENTOS))))
//                retorno.setQuantidadeAdiamentos(c.getInt(c.getColumnIndex(AtributosBancoDados.Agenda.QUANTIDADE_ADIAMENTOS)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.STATUS))))
//                retorno.setStatus(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.STATUS)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.STATUS_TRANSMISSAO))))
//                retorno.setStatusTransmissao(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.STATUS_TRANSMISSAO)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.TIPO_AGENDA))))
//                retorno.getTipoAgenda().setIdTipoAgenda(c.getInt(c.getColumnIndex(AtributosBancoDados.Agenda.TIPO_AGENDA)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.TIPO_CONCLUSAO))))
//                retorno.getTipoConclusao().setIdTipoConclusao(c.getLong(c.getColumnIndex(AtributosBancoDados.Agenda.TIPO_CONCLUSAO)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.TITULO_AGENDA))))
//                retorno.setTituloAgenda(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.TITULO_AGENDA)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.USUARIO_CADASTRO))))
//                retorno.setUsuarioCadastro(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.USUARIO_CADASTRO)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.ORIGEM_CADASTRO))))
//                retorno.setOrigemCadastro(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.ORIGEM_CADASTRO)));
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.ENVIADO))))
//                retorno.setEnviado(c.getInt(c.getColumnIndex(AtributosBancoDados.Agenda.ENVIADO)) > 0);
//            if (!c.isNull((c.getColumnIndex(AtributosBancoDados.Agenda.DATA_ENVIO))))
//                retorno.setDataEnvio(Formats.DATA_HORA_DB.parse(c.getString(c.getColumnIndex(AtributosBancoDados.Agenda.DATA_ENVIO))));
//        } catch (Exception e) {
//            Logs.mostrarLog(e);
//        }
//        return retorno;
//    }
//
//    public List<Agenda> buscarLista() {
//        List<Agenda> retorno = new ArrayList<Agenda>();
//        SQLiteDatabase db = DbHelper.getBancoLeitura(mContext, mDbLocal);
//        Cursor cursor = null;
//        try {
//            cursor = db.query(mTable, null, null, null, null, null, null);
//            while (cursor.moveToNext()) {
//                Agenda agenda = getObjetoPreenchido(cursor);
//                buscarObjetosRelacionados(agenda, db, true);
//                retorno.add(agenda);
//            }
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), e.getMessage());
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//        return retorno;
//    }
//
//    public List<Agenda> buscarListaVencidas() {
//        List<Agenda> retorno = new ArrayList<Agenda>();
//        SQLiteDatabase db = DbHelper.getBancoLeitura(mContext, mDbLocal);
//        Cursor cursor = null;
//        try {
//            String dataAtual = Formats.DATA_HORA_DB.format(Calendar.getInstance().getTime());
//
//            Parametro parametro = Parametro.buscar(mContext);
//
//            Calendar calendarAtualProgramado = Calendar.getInstance();
//            calendarAtualProgramado.add(Calendar.DATE, (parametro != null && parametro.getNumeroDiasAgendaVencida() != null) ? parametro.getNumeroDiasAgendaVencida() : 0);
//            String dataProgramada = Formats.DATA_HORA_DB.format(calendarAtualProgramado.getTime());
//
//            String whereClause = " (DataAgenda < '" + dataAtual + "' " +
//                    "OR DataAgenda <= '" + dataProgramada + "')" +
//                    " AND STATUS = '" + StatusAgenda.PENDENTE.toString() + "'";
//            cursor = db.query(mTable, null, whereClause, null, null, null, null);
//            while (cursor.moveToNext()) {
//                Agenda agenda = getObjetoPreenchido(cursor);
//                buscarObjetosRelacionados(agenda, db, true);
//                retorno.add(agenda);
//            }
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), e.getMessage());
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//        return retorno;
//    }
//
//    public Integer countAgendasVencidas() {
//        String dataAtual = Formats.DATA_MINUTO_DB.format(Calendar.getInstance().getTime());
//
//        Calendar calendarAtualProgramado = Calendar.getInstance();
//        calendarAtualProgramado.add(Calendar.DATE, Agenda.DIAS_PROGRAMADA);
//        String dataProgramada = Formats.DATA_MINUTO_DB.format(calendarAtualProgramado.getTime());
//
//        String whereClause = " (DataAgenda < '" + dataAtual + "' " +
//                "OR DataAgenda <= '" + dataProgramada + "')" +
//                " AND STATUS = '" + StatusAgenda.PENDENTE.toString() + "'";
//
//        SQLiteDatabase db = DbHelper.getBancoLeitura(mContext, mDbLocal);
//        Cursor mCount= db.rawQuery("select count(*) from " + mTable + " where " + whereClause, null);
//        mCount.moveToFirst();
//        return mCount.getInt(0);
//    }
//
//    protected void buscarObjetosRelacionados(Agenda a, SQLiteDatabase db, boolean carregarListas) {
//        try {
//            a.setSetorComercial(a.getSetorComercial().buscar(a.getSetorComercial().getDao(mContext, db)));
//            a.setCliente(a.getCliente().buscar(a.getCliente().getDao(mContext, db)));
//            a.setTipoAgenda(a.getTipoAgenda().buscar(a.getTipoAgenda().getDao(mContext, db)));
//            a.setTipoConclusao(a.getTipoConclusao().buscar(a.getTipoAgenda(), (TipoConclusaoAgendaDao) a.getTipoConclusao().getDao(mContext, db)));
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), e.getMessage());
//        }
//    }
//
//    public List<Agenda> buscarNaoEnviados() {
//        List<Agenda> retorno = new ArrayList<Agenda>();
//        SQLiteDatabase db = DbHelper.getBancoLeitura(mContext, mDbLocal);
//        Cursor c = null;
//        try {
//            String selection = AtributosBancoDados.Agenda.ENVIADO + "=0";
//            c = db.query(mTable, null, selection, null, null, null, AtributosBancoDados.Agenda.ID_AGENDA);
//            while (c.moveToNext()) {
//                Agenda agenda = getObjetoPreenchido(c);
//                buscarObjetosRelacionados(agenda, db, true);
//                retorno.add(agenda);
//            }
//        } catch (Exception e) {
//            Logs.mostrarLog(e);
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//            if (mDbLocal == null) {
//                db.close();
//            }
//        }
//        return retorno;
//    }
//
//    public List<Agenda> gerarTextos(Date dataHoraNomeArquivo) {
//        // Retorno
//        List<Agenda> retorno = new ArrayList<Agenda>();
//        // BufferedWriter
//        BufferedWriter writerAgenda = null;
//        try {
//            // Carrega os dados
//            List<Agenda> listaAgendaGerarTexto = buscarNaoEnviados();
//            if (listaAgendaGerarTexto == null || listaAgendaGerarTexto.size() == 0) {
//                return retorno;
//            }
//
//            for (Agenda agenda : listaAgendaGerarTexto) {
//                try {
//                    // Criando os arquivos
//                    Integer representanteId;
//                    if (agenda.getSetorComercial() != null && agenda.getSetorComercial().getIdColaborador() != null) {
//                        representanteId = agenda.getSetorComercial().getIdColaborador();
//                    } else {
//                        representanteId = Sessao.getSetorComercialLogado().getIdColaborador();
//                    }
//                    String nomeArquivoAgenda = String.format(Locale.getDefault(), "age%06d%s.txt",
//                            representanteId, Formats.DATA_HORA_MINUTO_SEGUNDO_EXPORTACAO.format(dataHoraNomeArquivo));
//                    File arquivoAgenda = new File(Environment.getExternalStorageDirectory().getPath() +
//                            ManipulacaoDiretorios.DIRETORIO_ENVIA, nomeArquivoAgenda);
//                    FileOutputStream outputStreamAgenda = new FileOutputStream(arquivoAgenda, true);
//                    writerAgenda = new BufferedWriter(new OutputStreamWriter(outputStreamAgenda, Constantes.CHARSET_UFT8));
//
//                    // Fixo "01"
//                    ManipulacaoArquivoTexto.escrever(writerAgenda, "01");
//                    // Código da Agenda (Tamanho=25 Tipo=C)
//                    if (agenda.getIdAgenda() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getIdAgenda(), 25, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 25);
//                    // Código do Setor Comercial (Tamanho=5 Tipo=C)
//                    if (agenda.getSetorComercial() != null && agenda.getSetorComercial().getIdSetorComercial() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getSetorComercial().getIdSetorComercial(), 5, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 5);
//                    // Código do Cliente (Tamanho=25 Tipo=C)
//                    if (agenda.getCliente() != null && agenda.getCliente().getIdCliente() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getCliente().getIdCliente(), 25, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 25);
//                    // Código da Agenda ERP (Tamanho=12 Tipo=N)
//                    if (agenda.getIdAgendaERP() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getIdAgendaERP(), 12);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 12);
//                    // Tipo da Agenda (Tamanho=6 Tipo=N)
//                    if (agenda.getTipoAgenda() != null && agenda.getTipoAgenda().getIdTipoAgenda() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getTipoAgenda().getIdTipoAgenda(), 6);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 6);
//                    // Data/Hora da Agenda (Tamanho=12 Tipo=C Formato=YYYYMMDDHHMM)
//                    if (agenda.getDataAgenda() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, Formats.DATA_HORA_MINUTO_EXPORTACAO.format(agenda.getDataAgenda()));
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 12);
//                    // Título da Agenda (Tamanho=60 Tipo=C)
//                    if (agenda.getTituloAgenda() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getTituloAgenda(), 60, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 60);
//                    // Data/Hora do Cadastro (Tamanho=12 Tipo=C Formato=YYYYMMDDHHMM)
//                    if (agenda.getDataCadastro() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, Formats.DATA_HORA_MINUTO_EXPORTACAO.format(agenda.getDataCadastro()));
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 12);
//                    // Usuário Cadastro (Tamanho=20 Tipo=C)
//                    if (agenda.getUsuarioCadastro() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getUsuarioCadastro(), 20, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 20);
//                    // Origem Cadastro (Tamanho=20 Tipo=C)
//                    if (agenda.getOrigemCadastro() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getOrigemCadastro(), 20);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 20);
//                    // Quantidade de Adiamentos (Tamanho=3 Tipo=N)
//                    if (agenda.getQuantidadeAdiamentos() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getQuantidadeAdiamentos(), 3);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 3);
//                    // Status (Tamanho=12 Tipo=C)
//                    if (agenda.getStatus() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getStatus(), 12, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 12);
//                    // Tipo Conclusão (Tamanho=6 Tipo=N)
//                    if (agenda.getTipoConclusao() != null && agenda.getTipoConclusao().getIdTipoConclusao() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getTipoConclusao().getIdTipoConclusao(), 6);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 6);
//                    // Data/Hora da Conclusão (Tamanho=12 Tipo=C Formato=YYYYMMDDHHMM)
//                    if (agenda.getDataConclusao() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, Formats.DATA_HORA_MINUTO_EXPORTACAO.format(agenda.getDataConclusao()));
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 12);
//                    // Agenda Origem (Tamanho=25 Tipo=C)
//                    if (agenda.getidAgendaOrigem() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getidAgendaOrigem(), 25, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 25);
//                    // Status Transmissão (Tamanho=12 Tipo=C)
//                    if (agenda.getStatusTransmissao() != null)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, agenda.getStatusTransmissao(), 12, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                    else
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "", 12);
//                    // Quebra de linha
//                    ManipulacaoArquivoTexto.escrever(writerAgenda, ManipulacaoString.QUEBRA_LINHA);
//
//                    if (agenda.getDescricaoAgenda() != null && !agenda.getDescricaoAgenda().trim().isEmpty()) {
//                        // Fixo "02"
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "02");
//                        // Fixo "01"
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "01");
//                        // Observação (Tamanho=1500 Tipo=C)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, ManipulacaoString.tirarQuebraDeLinha(agenda.getDescricaoAgenda()), 1500, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                        // Quebra de linha
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, ManipulacaoString.QUEBRA_LINHA);
//                    }
//                    if (agenda.getDescricaoConclusao() != null && !agenda.getDescricaoConclusao().trim().isEmpty()) {
//                        // Fixo "02"
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "02");
//                        // Fixo "02"
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, "02");
//                        // Observação (Tamanho=1500 Tipo=C)
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, ManipulacaoString.tirarQuebraDeLinha(agenda.getDescricaoConclusao()), 1500, ManipulacaoArquivoTexto.AlinhamentoTexto.ESQUERDA);
//                        // Quebra de linha
//                        ManipulacaoArquivoTexto.escrever(writerAgenda, ManipulacaoString.QUEBRA_LINHA);
//                    }
//                    // Concluiu a geração do texto, adiciona na lista de retorno
//                    retorno.add(agenda);
//                } catch (Exception e) {
//                    Log.e(getClass().getSimpleName(), e.getMessage());
//                } finally {
//                    try {
//                        if (writerAgenda != null) {
//                            writerAgenda.close();
//                        }
//                    } catch (IOException e) {
//                        Log.e(getClass().getSimpleName(), e.getMessage());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Logs.gravarLog(e.getMessage());
//        }
//        // Retornando
//        return retorno;
//    }
//
//    public String gerarId() {
//        return DbHelper.gerarId(mContext, mTable, mDbLocal);
//    }
//}
