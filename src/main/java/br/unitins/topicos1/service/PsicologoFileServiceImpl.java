package br.unitins.topicos1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.topicos1.model.Psicologo;
import br.unitins.topicos1.repository.PsicologoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PsicologoFileServiceImpl implements FileService {
    // ex = /user/janio/quarkus/images/psicologo
    private final String PATH_USER = System.getProperty("user.home")
        + File.separator + "quarkus"
        + File.separator + "images"
        + File.separator + "psicologo" + File.separator;

    @Inject
    PsicologoRepository psicologoRepository;
 
    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem) {
        Psicologo psicologo = psicologoRepository.findById(id);
        try {
            psicologo.setNomeImagem(salvarImagem(nomeImagem, imagem));
        } catch (IOException e) {
            throw new ValidationException("imagem", e.getMessage());
        }
    }

    private String salvarImagem(String nomeImagem, byte[] imagem) throws IOException {
        // verificar o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/gif", "image/png", "image/jpeg");
        if (!listMimeType.contains(mimeType)) 
            throw new IOException("Tipo de imagem não suportado.");

        // verificar o tamanho do arquivo - nao permitir maior que 10mb
        if (imagem.length > 1024 * 1024 * 10) {
            throw new IOException("Arquivo muito grande, tamanho máximo 10mb.");
        }
        
        // criar pasta quando nao existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists()) 
            diretorio.mkdirs();

        // gerar nome do arquivo 
        String nomeArquivo = UUID.randomUUID() 
                                + "." 
                                + mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String path = PATH_USER + nomeArquivo;

        // salvar o arquivo
        File file = new File(path);
        if (file.exists())
          throw new IOException("Este arquivo ja existe. Programador, tu deve melhorar esse codigo");

        // criar o arquivo no SO
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        fos.flush();
        fos.close();

        return nomeArquivo;
    }

    @Override
    public File download(String nomeImagem) {
       return new File(PATH_USER + nomeImagem);
    }
    
}
