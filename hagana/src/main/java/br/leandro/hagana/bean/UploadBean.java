/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.DAO;
import br.leandro.hagana.entidade.Foto;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import org.apache.pdfbox.io.ByteArrayPushBackInputStream;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class UploadBean implements Serializable {

    private static final long serialVersionUID = 123545641354634321L;

    private Part arquivo;
    private UploadedFile file;
    private String nome;
    private Foto foto;
    private String diretorioCliente = "";

    public void importa() {

        InputStream inputstream;
        try {

            saveFile();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

    private final String diretorioArquivos = "C:\\Users\\Leandro Code\\Documents\\NetBeansProjects\\Midia\\web\\restricted\\arquivos\\arquivos\\";

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public void saveFile() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
        String diretorio = ec.getRealPath("/");
       
         
        diretorioCliente = diretorio + "restrict\\arquivos\\contas\\" + SessionContext.getInstance().getClienteSelecionado().getConta() + "\\imagens\\";
    
        if(arquivo != null)
        {
        try ( InputStream input = arquivo.getInputStream()) {
            foto = new Foto();
            foto.setNome(nome);
            foto.setClienteFK(SessionContext.getInstance().getClienteSelecionado());
            foto.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
            foto.setDataCriacao(new Date());
            
            foto = (Foto) DAO.getInstance().insert(foto);
            
            if(foto != null)
            {
            Files.copy(input, new File(diretorioCliente, String.valueOf(foto.getIdfoto()) + ".jpg").toPath());
            alterarFoto(390, 260, String.valueOf(foto.getIdfoto()));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha!", " erro ao adicionar foto.")); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

 
    private void alterarFoto(int w, int h, String nome) throws IOException {
        BufferedImage img = null;
        FileInputStream f = null;

        //recupera a imagem do banco usando a entidade cadastro e carrega a foto no Label 
        try {

             f = new FileInputStream(diretorioCliente  +  nome + ".jpg");
             
             File file1 = new File(diretorioCliente  +  arquivo.getSubmittedFileName());
             
            
            img = ImageIO.read(f);

            BufferedImage new_img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(img, 0, 0, w, h, null);
            ImageIO.write(new_img, "JPG", new File(diretorioCliente + "_" + nome + ".jpg"));
            f.close();
            
        } catch (Exception ex) {
             f.close();
        }
        finally{
            
        }

    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
