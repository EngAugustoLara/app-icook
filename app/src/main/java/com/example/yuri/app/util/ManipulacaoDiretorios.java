//package com.example.yuri.app.util;
//
//import android.os.Environment;
//import android.util.Log;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileFilter;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.nio.channels.FileChannel;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
//
///**
// * Created by yuri on 14/09/17.
// */
//
//
//public class ManipulacaoDiretorios {
//    static final String TAG = ManipulacaoDiretorios.class.getSimpleName();
//
//    public static final String DIRETORIO_SAV = "/sav/";
//    public static final String DIRETORIO_LOGO = "/sav/logo/";
//    public static final String DIRETORIO_RECEBE = "/sav/recebe/";
//    public static final String DIRETORIO_ENVIA = "/sav/envia/";
//    public static final String DIRETORIO_ENVIA_ULTIMOS = "/sav/envia/ultimos/";
//    public static final String DIRETORIO_EMAIL = "/sav/email/";
//    public static final String DIRETORIO_EMAIL_ENVIA = "/sav/email/envia/";
//    public static final String DIRETORIO_EMAIL_RECEBE = "/sav/email/recebe/";
//    public static final String DIRETORIO_EMAIL_PROBLEMA = "/sav/email/problema/";
//    public static final String DIRETORIO_CORREIO = "/sav/correio/";
//    public static final String DIRETORIO_CORREIO_ENVIA = "/sav/correio/envia/";
//    public static final String DIRETORIO_CORREIO_RECEBE = "/sav/correio/recebe/";
//    public static final String DIRETORIO_CORREIO_LIDAS = "/sav/correio/lidas/";
//    public static final String DIRETORIO_RESUMO = "/sav/resumo/";
//    public static final String DIRETORIO_RESUMO_PEDIDO = "/sav/resumo_pedido/";
//    public static final String DIRETORIO_RESUMO_ORCAMENTO = "/sav/resumo_orcamento/";
//    public static final String DIRETORIO_LOG = "/sav/log/";
//    public static final String DIRETORIO_BACKUP = "/sav/backup/";
//    public static final String DIRETORIO_BACKUP_ENVIO = "/sav/backup_envio/";
//    public static final String DIRETORIO_ATUALIZACOES = "/sav/atualizacoes/";
//    public static final String DIRETORIO_FOTOS_OCORRENCIAS = "/sav/fotos_ocorrencia/";
//    public static final String DIRETORIO_FOTOS_OCORRENCIAS_NAO_ENVIADAS = "/sav/fotos_ocorrencia/nao_enviadas/";
//    public static final String DIRETORIO_ARQUIVOS_OCORRENCIAS = "/sav/arquivos_ocorrencia/";
//    public static final String DIRETORIO_ARQUIVOS_OCORRENCIAS_NAO_ENVIADAS = "/sav/arquivos_ocorrencia/nao_enviados/";
//
//    static final int BUFFER = 2048;
//
//    /**
//     * Apaga os arquivos que est�o dentro de um determinado diret�rio.
//     * @param pCaminhoDiretorio  Caminho do diret�rio que ser� 'limpado'.
//     */
//    public static void apagarArquivos(String pCaminhoDiretorio) {
//        try {
//            File dir = new File(pCaminhoDiretorio);
//            if (!dir.exists())
//                return;
//
//            File[] arquivos = dir.listFiles();
//
//            for (File arquivo : arquivos) {
//                if (arquivo.isDirectory() && arquivo.list().length > 0) {
//                    apagarArquivos(arquivo.getPath());
//                    arquivo.delete();
//                } else {
//                    arquivo.delete();
//                }
//            }
//        } catch (Exception e) {
//            Log.e("ApagarArquivosDiretorio", e.getMessage());
//        }
//    }
//
//    /**
//     * Apaga um determinado arquivo.
//     * @param pCaminhoArquivo  Caminho do arquivo que ser� exclu�do.
//     */
//    public static void apagarArquivo(String pCaminhoArquivo) {
//        try {
//            File file = new File(pCaminhoArquivo);
//            if (!file.exists())
//                return;
//
//            if (file.isFile())
//                file.delete();
//
//        } catch (Exception e) {
//            Log.e("apagarArquivo", e.getMessage());
//        }
//    }
//
//    public static void apagarArquivosAtualizacao(String caminhoDiretorio) {
//        apagarArquivosAtualizacao(caminhoDiretorio, "");
//    }
//
//    /**
//     * Apagar instalador do SAV que est� em um determinado diret�rio.
//     * @param caminhoDiretorio  Caminho do diret�rio que ter� todos os intaladores android que nele estiver apagados.
//     */
//    public static void apagarArquivosAtualizacao(String caminhoDiretorio, String appName) {
//        try {
//            File dir = new File(caminhoDiretorio);
//            if (!dir.exists())
//                return;
//
//            File[] arquivos = dir.listFiles();
//            if (arquivos.length == 0)
//                return;
//
//            for (File arquivo : arquivos) {
//                String fileName = ManipulacaoString.tirarAcentos(ManipulacaoString.converterParaMinuscula(arquivo.getName()));
//                appName = ManipulacaoString.tirarAcentos(ManipulacaoString.converterParaMinuscula(appName));
//
//                if ((fileName.contains(appName) || fileName.isEmpty()) && fileName.endsWith(".apk")) {
//                    arquivo.delete();
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
//
//    /**
//     *
//     * @param caminhoDiretorio  Verifica a existencia de um determinado diret�rio, atravez do seu caminho.
//     * @return  TRUE - Existe um diret�rio no caminho especificado. <br /> FALSE - N�o existe um diret�rio no caminho especificado.
//     */
//    public static boolean existeDiretorio(String caminhoDiretorio) throws Exception {
//        File arquivo = new File(caminhoDiretorio);
//
//        if(caminhoDiretorio == null || caminhoDiretorio.trim().length() == 0) {
//            return false;
//        }
//        return arquivo.exists();
//    }
//
//    /**
//     * Exclui o diretorio que est� no caminho especificado no par�metro 'pCaminhoDiretorio'.
//     * @param pCaminhoDiretorio  Caminho do diret�rio que ser� exluido.
//     * @return  TRUE - Diret�rio foi excluido com sucesso<br /> FASLE - N�o foi poss�vel excluir o diret�rio especificado.
//     * @throws Exception
//     */
//    public static boolean apagarDiretorio(String pCaminhoDiretorio) throws Exception {
//        File diretorio = new File(pCaminhoDiretorio);
//
//        if(pCaminhoDiretorio == null || pCaminhoDiretorio.length() == 0) {
//            throw new Exception("Caminho ou diret�rio inv�lido!");
//        }
//        if(diretorio.isDirectory()) {
//            return diretorio.delete();
//        }
//        return false;
//    }
//
//    /**
//     * Apaga os arquivos que est�o na pasta de backup dos envios do SAV;
//     * @param pQuantidadeDias  Quantidade de dias pode-se apagar um arquivo de backup ap�s sua cria��o.
//     */
//    public static void apagarBackupEnvia(int pQuantidadeDias) throws ParseException {
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            String diretorioArquivos = Environment.getExternalStorageDirectory().getPath() + DIRETORIO_BACKUP_ENVIO;
//
//            File diretorio = new File(diretorioArquivos);
//            if(diretorio == null || !diretorio.exists())
//                return;
//
//            File[] arquivos = diretorio.listFiles();
//            if(arquivos == null || arquivos.length == 0)
//                return;
//
//            Calendar dia = Calendar.getInstance();
//            dia.add(Calendar.DAY_OF_MONTH, pQuantidadeDias * -1);
//            Date dataApagar = dia.getTime();
//
//            for(int i = 0 ; i < arquivos.length ; i++) {
//                try {
//                    Date dataPasta = Formats.DATA_BR_TRACOS.parse(arquivos[i].getName());
//
//                    if(dataPasta.compareTo(dataApagar) <= 0) {
//                        ManipulacaoDiretorios.apagarArquivos(arquivos[i].getPath());
//                        ManipulacaoDiretorios.apagarArquivos(arquivos[i].getPath());
//                    }
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                    continue;
//                }
//            }
//        }
//    }
//
//    /**
//     * Cria um diret�rio com o caminho especificado no par�metro 'pCaminhoDiretorio'.
//     * @param pCaminhoDiretorio  Caminho onde o diret�rio criado ir� ficar.
//     * @return  TRUE - Diret�rio foi criado com sucesso<br /> FASLE - N�o foi poss�vel criar o diret�rio especificado.
//     */
//    public static boolean criarDiretorio(String pCaminhoDiretorio) throws IllegalArgumentException {
//        File arquivo = new File(pCaminhoDiretorio);
//
//        if(pCaminhoDiretorio == null || pCaminhoDiretorio.trim().length() == 0) {
//            throw new IllegalArgumentException("Caminho ou diret�rio inv�lido - criarDiretorio(String caminho)");
//        }
//        return arquivo.mkdirs();
//    }
//
//    /**
//     * Cria os diret�rios padr�es do SAV.
//     */
//    public static void criarDiretoriosPadroes() {
//        try {
//            String rootPath = "";
//            String state = Environment.getExternalStorageState();
//
//            if (state.equals(Environment.MEDIA_MOUNTED) && !state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
//                rootPath = Environment.getExternalStorageDirectory().getPath();
//
//                new File(rootPath + DIRETORIO_SAV).mkdirs();
//                new File(rootPath + DIRETORIO_RECEBE).mkdirs();
//                new File(rootPath + DIRETORIO_ENVIA).mkdirs();
//                new File(rootPath + DIRETORIO_ENVIA_ULTIMOS).mkdirs();
//                new File(rootPath + DIRETORIO_EMAIL).mkdirs();
//                new File(rootPath + DIRETORIO_EMAIL_ENVIA).mkdirs();
//                new File(rootPath + DIRETORIO_EMAIL_RECEBE).mkdirs();
//                new File(rootPath + DIRETORIO_EMAIL_PROBLEMA).mkdirs();
//                new File(rootPath + DIRETORIO_CORREIO).mkdirs();
//                new File(rootPath + DIRETORIO_CORREIO_ENVIA).mkdirs();
//                new File(rootPath + DIRETORIO_CORREIO_RECEBE).mkdirs();
//                new File(rootPath + DIRETORIO_CORREIO_LIDAS).mkdirs();
//                new File(rootPath + DIRETORIO_RESUMO).mkdirs();
//                new File(rootPath + DIRETORIO_LOG).mkdirs();
//                new File(rootPath + DIRETORIO_ATUALIZACOES).mkdirs();
//                new File(rootPath + DIRETORIO_RESUMO_PEDIDO).mkdirs();
//                new File(rootPath + DIRETORIO_RESUMO_ORCAMENTO).mkdirs();
//                new File(rootPath + DIRETORIO_BACKUP_ENVIO).mkdirs();
//                new File(rootPath + DIRETORIO_BACKUP).mkdirs();
//                new File(rootPath + DIRETORIO_LOGO).mkdirs();
//                new File(rootPath + DIRETORIO_FOTOS_OCORRENCIAS).mkdirs();
//                new File(rootPath + DIRETORIO_FOTOS_OCORRENCIAS_NAO_ENVIADAS).mkdirs();
//                new File(rootPath + DIRETORIO_ARQUIVOS_OCORRENCIAS).mkdirs();
//                new File(rootPath + DIRETORIO_ARQUIVOS_OCORRENCIAS_NAO_ENVIADAS).mkdirs();
//            }
//        } catch (Exception e) {
//            Log.e("criarDiretorios", e.getMessage());
//        }
//    }
//
//    /**
//     * Copia um determinado arquivo de um diret�rio para outro.
//     * @param origem   Caminho onde est� localizado o arquivo.
//     * @param destino  Caminho onde se deseja copiar o arquivo.
//     */
//    public static void copiarArquivo(String origem, String destino){
//        try {
//            FileInputStream inputStream = new FileInputStream(origem);
//            FileOutputStream outputStream = new FileOutputStream(destino.toLowerCase());
//
//            FileChannel channelOrigem = inputStream.getChannel();
//            FileChannel channelDestino = outputStream.getChannel();
//            channelOrigem.transferTo(0, channelOrigem.size(), channelDestino);
//
//            inputStream.close();
//            outputStream.close();
//        } catch (Exception e) {
//            Log.d("copiarArquivo", e.getMessage());
//        }
//    }
//
//    /**
//     * Copia o banco de backup (que ficar� no gerenciado e copiado para a pasta 'sav/backup/' quando preciso) para o banco que osistema usa.
//     * @param pNomeBanco		Nome do banco do projeto.
//     * @param pNomePacoteBanco  Pacote do banco, que est� na pasta 'data' do android.
//     * @return  				TRUE - O banco foi restaurado com sucesso<br /> FASLE - N�o foi poss�vel restaurar o banco.
//     */
//    @SuppressWarnings("resource")
//    public static boolean restaurarBanco(String pNomeBanco, String pNomePacoteBanco) {
//        try {
//            File sdCard = Environment.getExternalStorageDirectory();
//            File data = Environment.getDataDirectory();
//
//            pNomeBanco = ajustarNomeBanco(pNomeBanco);
//            if (sdCard.canWrite()) {
//                String currentDBPath = String.format("%s%s", DIRETORIO_BACKUP, pNomeBanco);
//                String backupDBPath = String.format("//data//%s//databases//%s", pNomePacoteBanco, pNomeBanco);
//                File backupDB = new File(data, backupDBPath);
//                File currentDB = new File(sdCard, currentDBPath);
//
//                FileChannel src = new FileInputStream(currentDB).getChannel();
//                FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                dst.transferFrom(src, 0, src.size());
//                src.close();
//                dst.close();
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * Verifica se o nome do arquivo possui a exten��o '.db', e caso n�o exista ela � colocada.
//     * @param pNomeBanco  Nome do banco que ser� verificado.
//     * @return 			  Nome do banco correto.
//     */
//    private static String ajustarNomeBanco(String pNomeBanco) {
//        if (ManipulacaoString.converterParaMaiusculo(pNomeBanco.trim()).endsWith(".DB")) {
//            return pNomeBanco;
//        }
//        return String.format("%s.db", pNomeBanco);
//    }
//
//    /**
//     * Verifica a existencia de arquivos para o SAV importar.
//     * @return  TRUE - Existe um ou mais aquivos para o SAV importar<br /> FASLE - N�o existe aquivos para o SAV importar.
//     */
//    public static boolean existeArquivosParaImportar() {
//        return existeArquivosParaImportar(false);
//    }
//
//    /**
//     * Verifica a existencia de arquivos para o SAV importar.
//     * @param pVerificarEmailRecebe  TRUE - Verifica se existem arquivos para importar nos diret�rios '/sav/recebe/' e '/sav/email/recebe/'<br />
//     * 								 FALSE - Verifica se existem arquivos para importar apenas no diret�rio '/sav/recebe/'
//     * @return  					 TRUE - Existe um ou mais aquivos para o SAV importar<br /> FASLE - N�o existe aquivos para o SAV importar.
//     */
//    public static boolean existeArquivosParaImportar(boolean pVerificarEmailRecebe) {
//        File fileRecebe = new File(Environment.getExternalStorageDirectory().getPath() + DIRETORIO_RECEBE);
//        File fileEmailRecebe = new File(Environment.getExternalStorageDirectory().getPath() + DIRETORIO_EMAIL_RECEBE);
//
//        if (pVerificarEmailRecebe) {
//            return (fileRecebe.listFiles().length > 0) || (fileEmailRecebe.listFiles().length > 0);
//        } else {
//            return (fileRecebe.listFiles().length > 0);
//        }
//    }
//
//    public static void compactar(String[] arquivos, String nomeArquivoZip) {
//        String[] files = arquivos;
//        String zipFile = nomeArquivoZip;
//        ZipOutputStream zipOutputStream = null;
//
//        try {
//            if (files == null || files.length == 0) {
//                return;
//            }
//
//            FileOutputStream dest = new FileOutputStream(zipFile);
//
//            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(dest));
//
//            for (int i = 0; i < files.length; i++) {
//                addFileToZip("", files[i], zipOutputStream);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (zipOutputStream != null) {
//                try {
//                    zipOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private static void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws IOException {
//        File file = new File(srcFile);
//
//        if (file.isDirectory()) {
//            addFolderToZip(path, srcFile, zip);
//        } else {
//            FileInputStream fi = null;
//            try {
//                int length;
//                byte[] buffer = new byte[BUFFER];
//                fi = new FileInputStream(srcFile);
//
//                if (path != null && !path.isEmpty()) {
//                    zip.putNextEntry(new ZipEntry(path + File.separator + file.getName()));
//                } else {
//                    zip.putNextEntry(new ZipEntry(file.getName()));
//                }
//
//                while ((length = fi.read(buffer)) > 0) {
//                    zip.write(buffer, 0, length);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (fi != null) {
//                    fi.close();
//                }
//            }
//        }
//    }
//
//    private static void addOnlyFilesToZip(String path, String srcFile, ZipOutputStream zip) throws IOException {
//        File file = new File(srcFile);
//
//        if (file.isDirectory()) {
//            addFolderContentsToZip(srcFile, zip);
//        } else {
//            FileInputStream fi = null;
//            try {
//                int length;
//                byte[] buffer = new byte[BUFFER];
//                fi = new FileInputStream(srcFile);
//
//                if (path != null && !path.isEmpty()) {
//                    zip.putNextEntry(new ZipEntry(path + File.separator + file.getName()));
//                } else {
//                    zip.putNextEntry(new ZipEntry(file.getName()));
//                }
//
//                while ((length = fi.read(buffer)) > 0) {
//                    zip.write(buffer, 0, length);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (fi != null) {
//                    fi.close();
//                }
//            }
//        }
//    }
//
//    private static void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
//        File folder = new File(srcFolder);
//        for (String fileName : folder.list()) {
//            if (path != null && !path.isEmpty()) {
//                addFileToZip(path + File.separator + folder.getName(), srcFolder + File.separator + fileName, zip);
//            } else {
//                addFileToZip(folder.getName(), srcFolder + File.separator + fileName, zip);
//            }
//        }
//    }
//
//    private static void addFolderContentsToZip(String srcFolder, ZipOutputStream zip) throws IOException {
//        File folder = new File(srcFolder);
//        for (String fileName : folder.list()) {
//            addFileToZip("", srcFolder + File.separator +  fileName, zip);
//        }
//    }
//
//    public static void descompactar(String _zipFile, String _location) {
//        FileInputStream fin = null;
//        FileOutputStream fout = null;
//        ZipInputStream zin = null;
//
//        try {
//            _dirChecker("", _location);
//
//            fin = new FileInputStream(_zipFile);
//
//            zin = new ZipInputStream(fin);
//            ZipEntry ze = null;
//
//            BufferedOutputStream bos = null;
//            ze = zin.getNextEntry();
//
//            while (ze != null && ze.getSize() != -1) {
//                Log.v("Decompress", "Unzipping " + ze.getName());
//
//                if (ze.isDirectory()) {
//                    _dirChecker(ze.getName(), _location);
//                } else {
//                    byte[] buffer = new byte[4096];
//                    fout = new FileOutputStream(_location + ze.getName());
//                    bos = new BufferedOutputStream(fout, buffer.length);
//
//                    int size;
//
//                    while ((size = zin.read(buffer, 0, buffer.length)) != -1)
//                        bos.write(buffer, 0, size);
//
//                    zin.closeEntry();
//
//                    bos.flush();
//                    bos.close();
//
//                    fout.flush();
//                    fout.close();
//
//                    ze = zin.getNextEntry();
//                }
//            }
//        } catch (IOException e) {
//            Log.e("Decompress", e.getLocalizedMessage());
//        } finally {
//            try {
//                if (fin != null) {
//                    fin.close();
//                }
//            } catch (Exception e2) {
//                Log.e("Decompress", e2.getLocalizedMessage());
//            }
//            try {
//                if (zin != null) {
//                    zin.close();
//                }
//            } catch (Exception e2) {
//                Log.e("Decompress", e2.getLocalizedMessage());
//            }
//            try {
//                if (fout != null) {
//                    fout.close();
//                }
//            } catch (Exception e2) {
//                Log.e("Decompress", e2.getLocalizedMessage());
//            }
//        }
//    }
//
//    private static void _dirChecker(String dir, String _location) {
//        File f = new File(_location + dir);
//
//        if (!f.isDirectory()) {
//            f.mkdirs();
//        }
//    }
//
//    public static String[] arquivosEnvio() {
//        String[] retorno = new String[] {};
//        try {
//            File dir = new File(Environment.getExternalStorageDirectory().getPath() + DIRETORIO_ENVIA);
//            if (dir.exists()) {
//                FileFilter filter = new FileFilter() {
//                    @Override
//                    public boolean accept(File pathname) {
//                        return pathname.isFile();
//                    }
//                };
//                File[] files = dir.listFiles(filter);
//                retorno = new String[files.length];
//                for (int i = 0; i < files.length; i++) {
//                    retorno[i] = files[i].getPath();
//                }
//            }
//        } catch (Exception e) {
//            Log.e("arquivosEnvio", e.getMessage());
//        }
//        return retorno;
//    }
//
//    /**
//     * File -> List<String>
//     * processa o arquivo de importacao
//     * e coloca suas linhas em uma lista
//     */
//    public static List<String> processaArquivo(File importacao) throws ManipulacaoDiretoriosException{
//        try {
//            List<String> linhas = new ArrayList<String>();
//            FileReader readerPreco = new FileReader(importacao);
//            BufferedReader bufferPreco = new BufferedReader(readerPreco);
//            try {
//                while (bufferPreco.ready())
//                    linhas.add(bufferPreco.readLine());
//                bufferPreco.close();
//            } catch (IOException e) {
//                throw new ManipulacaoDiretoriosException(e.getMessage());
//            }
//            return linhas;
//        }catch (FileNotFoundException e) {
//            throw new ManipulacaoDiretoriosException(e.getMessage());
//        }
//    }
//
//    public static File getDiretorioRecebe() throws ManipulacaoDiretoriosException {
//        File retorno = null;
//        try {
//            String caminhoRecebe = Environment.getExternalStorageDirectory() + DIRETORIO_RECEBE;
//            retorno = new File(caminhoRecebe);
//        } catch (Exception e) {
//            throw new ManipulacaoDiretoriosException(e.getMessage());
//        }
//        return retorno;
//    }
//
//    public static void apagarArquivosApkDoDiretorioDownloads(final String pNomeArquivoSemExtensao) {
//        try {
//            final String nomeApk = pNomeArquivoSemExtensao.toUpperCase();
//            File diretorioDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            File[] arquivosApk = diretorioDownloads.listFiles(new FilenameFilter() {
//                @Override
//                public boolean accept(File dir, String filename) {
//                    return filename.endsWith(".apk") && filename.toUpperCase().startsWith(nomeApk);
//                }
//            });
//
//            if (arquivosApk != null) {
//                for (File arquivo : arquivosApk) {
//                    arquivo.delete();
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
//
//    public static List<String> getNomesArquivosDiretorio(String pCaminhoDiretorio, boolean retirarExtensao) {
//        List<String> retorno = null;
//        try {
//            File diretorio = new File(pCaminhoDiretorio);
//            if (diretorio.exists() && diretorio.isDirectory()) {
//                String[] nomes = diretorio.list();
//                retorno = new ArrayList<String>(nomes.length);
//                for (int i = 0; i < nomes.length; i++) {
//                    if (!retirarExtensao) {
//                        retorno.add(nomes[i]);
//                    } else {
//                        retorno.add(nomes[i].substring(0, nomes[i].lastIndexOf(".")));
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//        return retorno;
//    }
//}
