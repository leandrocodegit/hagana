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
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.Part; 

/**
 *
 * @author Leandro Oliveira
 */
@ManagedBean
@ViewScoped
public class UploadBean implements Serializable {

    private static final long serialVersionUID = 123545641354634321L;

    private Part file; 
    private String nome;
    private Foto foto;
    private String diretorioCliente = "";
    private DAO dao = new DAO();

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Salva object no bd e jpg no diretorio
    public void uploadFile() {

        //Recupera path absoluto
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String diretorio = ec.getRealPath("/");

        diretorioCliente = diretorio + "restrict\\arquivos\\contas\\" + SessionContext.getInstance().getClienteSelecionado().getConta() + "\\imagens\\";

        if (file != null) {
            try ( InputStream input = file.getInputStream()) {
                foto = new Foto();
                foto.setNome(nome);
                foto.setClienteFK(SessionContext.getInstance().getClienteSelecionado());
                foto.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
                foto.setDataCriacao(new Date());

                foto = (Foto) dao.insert(foto);

                if (foto != null) {
                    Files.copy(input, new File(diretorioCliente, String.valueOf(foto.getIdfoto()) + ".jpg").toPath());
                    alterarFoto(390, 260, String.valueOf(foto.getIdfoto()));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "!", "Foto salva com sucesso."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha!", " erro ao adicionar foto."));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Salva uma copia jpg com tamanho reduzido w h
    private void alterarFoto(int w, int h, String nome) throws IOException {
        BufferedImage img = null;
        FileInputStream f = null;

        //recupera a imagem do banco usando a entidade cadastro e carrega a foto no Label 
        try {

            f = new FileInputStream(diretorioCliente + nome + ".jpg");

            img = ImageIO.read(f);

            BufferedImage new_img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(img, 0, 0, w, h, null);
            ImageIO.write(new_img, "JPG", new File(diretorioCliente + "_" + nome + ".jpg"));
            f.close();

        } catch (Exception ex) {
            f.close();
        } finally {

        }

    }
//Send mensage pagina usuario

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
