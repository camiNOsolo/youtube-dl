package yotubedl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class youtubedl extends javax.swing.JFrame implements ActionListener {

    public youtubedl() {
        initComponents(); //Carga todos los componentes de JFrame
        this.pack(); //Método que configura el marco automaticamente
        opciones.add(NQ); //RadioButton
        opciones.add(EA); //RadioButton
        opciones.add(MQ); //RadioButton 
        MQ.setSelected(true); //Seleccionar por defecto maxima calidad
        barra();
        
    }
    
    //Construye el comando en un String con las opciones
    String comandoYoutube() {
        String comando = "";
        String carpeta = directorio.getText();
        String dire = url.getText();
        if (NQ.isSelected()) {
            comando = "youtube-dl " + dire + " -o " + carpeta + "/%(title)s-%(id)s.%(ext)s";
        }
        if (EA.isSelected()) {
            comando = "youtube-dl --extract-audio --audio-format=mp3 " + dire + " -o " + carpeta + "/%(title)s-%(id)s.%(ext)s";
        }
        if (MQ.isSelected()) {
            comando = "youtube-dl --max-quality=mp4 " + dire + " -o " + carpeta + "/%(title)s-%(id)s.%(ext)s";
        }
        return comando;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("No está soportado");
    }

    //Muestra el título del video que se esta descargando
    class titulo extends Thread {

        @Override
        public void run() {
            String comando = "youtube-dl --get-title " + url.getText(); 
            String nombrevideo;
            Process x;
            try {
                x = Runtime.getRuntime().exec(comando);
                BufferedReader salida_descarga = new BufferedReader(new InputStreamReader(
                        x.getInputStream()));
                nombrevideo = salida_descarga.readLine();
                descargainfo.setText("Descargando: " + nombrevideo);
            } catch (IOException ex) {
                descargainfo.setText("URL no válida");
            }
        }

    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        editar1 = new javax.swing.JPopupMenu();
        pegar1 = new javax.swing.JMenuItem();
        opciones = new javax.swing.ButtonGroup();
        editar1 = new javax.swing.JPopupMenu();
        copiar1 = new javax.swing.JMenuItem();
        pegar1 = new javax.swing.JMenuItem();
        editar2 = new javax.swing.JPopupMenu();
        copiar2 = new javax.swing.JMenuItem();
        pegar2 = new javax.swing.JMenuItem();
        url = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        directorio = new javax.swing.JTextField();
        Mostrar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Detener = new javax.swing.JButton();
        NQ = new javax.swing.JRadioButton();
        MQ = new javax.swing.JRadioButton();
        EA = new javax.swing.JRadioButton();
        Descargar = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        progress = new javax.swing.JLabel();
        URL = new javax.swing.JLabel();
        descargainfo = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        salir = new javax.swing.JMenuItem();

        
        copiar1.setText("Copiar");
        copiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarActionPerformed(evt);
            }
        });
        editar1.add(copiar1);

        pegar1.setText("Pegar");
        pegar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegarActionPerformed(evt);
            }
        });
        editar1.add(pegar1);

        copiar2.setText("Copiar");
        copiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiar2ActionPerformed(evt);
            }
        });
        editar2.add(copiar2);

        pegar2.setText("Pegar");
        pegar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pegar2ActionPerformed(evt);
            }
        });
        editar2.add(pegar2);

        // Configuración de JFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("www.toString.es");
        setResizable(false);
      
        url.setComponentPopupMenu(editar1);

        jLabel1.setFont(new java.awt.Font("Qlassik Bold", 0, 18));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Guardar en:");

        directorio.setComponentPopupMenu(editar2);

        Mostrar.setFont(new java.awt.Font("Qlassik Bold", 0, 14));
        Mostrar.setText("Cambiar");
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        NQ.setForeground(new java.awt.Color(0, 0, 0));
        NQ.setText("Calidad Normal");

        MQ.setForeground(new java.awt.Color(0, 0, 0));
        MQ.setText("Máxima Calidad");

        EA.setForeground(new java.awt.Color(0, 0, 0));
        EA.setText("Extraer Mp3");

        Descargar.setFont(new java.awt.Font("Qlassik Bold", 0, 18));
        Descargar.setText("DESCARGAR");
        Descargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescargarActionPerformed(evt);
            }
        });

        Limpiar.setFont(new java.awt.Font("Qlassik Bold", 0, 18));
        Limpiar.setText("LIMPIAR");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) { 
             LimpiarActionPerformed(evt);
          }
        });
        
        Detener.setFont(new java.awt.Font("Qlassik Bold", 0, 18));
        Detener.setText("DETENER");
        Detener.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) { 
             DetenerActionPerformed(evt);
          }
        });
        
        progress.setForeground(new java.awt.Color(0, 0, 0));
        progress.setText("Información de la descarga: ");

        URL.setFont(new java.awt.Font("Qlassik Bold", 0, 18));
        URL.setForeground(new java.awt.Color(0, 0, 0));
        URL.setText("URL del vídeo:");

        descargainfo.setFont(new java.awt.Font("Ubuntu", 1, 15));
        descargainfo.setForeground(new java.awt.Color(0, 0, 0));
        descargainfo.setText("Descargando: ");

        Archivo.setText("Menu");

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        
        Archivo.add(salir);
        MenuBar.add(Archivo);
        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
       
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(10, 10) // Espacio vacío a la izquierda
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel1)
                                                                .addComponent(URL))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(url, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(directorio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(NQ, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(20, 20, 20)
                                                .addGroup(layout.createSequentialGroup()
                                                         .addComponent(MQ, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                         .addGap(30, 30, 30)
                                                .addGroup(layout.createSequentialGroup()
                                                         .addComponent(EA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                         .addGap(20, 20, 20))))))
                                                .addGroup(layout.createSequentialGroup()
                                                          .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                          .addComponent(Descargar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                          .addComponent(Detener, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)))
                              
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(descargainfo, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(url, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(URL))
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(directorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Mostrar))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(NQ)
                                                .addComponent(MQ)
                                                .addComponent(EA))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(Descargar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Detener, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(descargainfo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGap(10, 10, 10)))
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(progress)
                        .addGap(10, 10, 10))
        );

        pack();
    }    

    class MyProgressUI extends BasicProgressBarUI {

        private Rectangle r = new Rectangle();

        protected void paintIndeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            r = getBox(r);
            g.setColor(progressBar.getForeground());
            g.fillOval(r.x, r.y, r.width, r.height);
        }
    }
    
    //Thread descarga del video
    class descargarvideo extends Thread {

        @Override
        public void run() {
            jProgressBar1.setUI(new MyProgressUI());
            String download = comandoYoutube();
            String salida;
            String error;
            Integer i = 0;
            try {
                Process p = Runtime.getRuntime().exec(download);

                BufferedReader salida_descarga = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                BufferedReader error_descarga = new BufferedReader(new InputStreamReader(
                        p.getErrorStream()));
                while ((salida = salida_descarga.readLine()) != null) {
                    progress.setText(salida);
                    i++;
                    jProgressBar1.setForeground(Color.red);
                    jProgressBar1.setIndeterminate(true);
                }
                while ((error = error_descarga.readLine()) != null) {
                    JOptionPane.showMessageDialog(null, error, "Error en la descarga!", 0);
                    jProgressBar1.setIndeterminate(false);
                }
                if (i > 10 && salida_descarga.readLine() == null) {
                    //JOptionPane.showMessageDialog(null, "Descarga Completa!", "Completo!", 1);
                    jProgressBar1.setIndeterminate(false);
                    jProgressBar1.setStringPainted(true);
                    jProgressBar1.setString("PROCESO FINALIZADO");
                }
            } catch (IOException e) {

            } catch (HeadlessException e) {
            }

        }
    }

    private void copiarclipboard(String copy) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection urlcopy = new StringSelection(copy);
        cb.setContents(urlcopy, urlcopy);
    }

    private void pegarclipboard(JTextField lugarpegar) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable copy = cb.getContents(this);
        DataFlavor dataFlavorStringJava = null;
        String texto = null;
        try {
            dataFlavorStringJava = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");
            if (copy.isDataFlavorSupported(dataFlavorStringJava)) {
                texto = (String) copy.getTransferData(dataFlavorStringJava);
            }
        } catch (Exception ex) {
            Logger.getLogger(youtubedl.class.getName()).log(Level.SEVERE, null, ex);
        }
        lugarpegar.setText(texto);
    }

    // Ejecuta un JFileChooser
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        try{ 
            if(selectorArchivo.showSaveDialog(null)==selectorArchivo.APPROVE_OPTION){
                directorio.setText(selectorArchivo.getSelectedFile().getAbsolutePath());
            } } catch (Exception ex){
                ex.printStackTrace();
            }
      
    }
    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        url.setText("");
        directorio.setText("");
        MQ.setSelected(true);
    }
               
    // Armando la descarga
    private void DescargarActionPerformed(java.awt.event.ActionEvent evt) {
        Thread hilo = new descargarvideo();
        Thread hilogt = new titulo();
        if (!"".equals(url.getText())) {
            hilo.start();
            hilogt.start();
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa alguna URL!", "ERROR", 0);
        }

    }
    // Métodos
    private void copiarActionPerformed(java.awt.event.ActionEvent evt) {
        copiarclipboard(url.getSelectedText());
    }

    private void pegarActionPerformed(java.awt.event.ActionEvent evt) {
        pegarclipboard(url);
    }

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {
        confirmarSalida();
        
    }

    private void copiar2ActionPerformed(java.awt.event.ActionEvent evt) {
        copiarclipboard(directorio.getSelectedText());
    }

    private void pegar2ActionPerformed(java.awt.event.ActionEvent evt) {
        pegarclipboard(directorio);
    }

    private void confirmarSalida(){
        int i =JOptionPane.showConfirmDialog(this,"¿Realmente Desea Salir?","Confirmar Salida",JOptionPane.YES_NO_OPTION);
        if(i==0){
        System.exit(0);
        }
   }
    private void barra(){
        jProgressBar1.setUI(new MyProgressUI());
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setString("ESPERANDO A QUE PULSE EL BOTÓN DESCARGA");
    }
    

    private void DetenerActionPerformed(java.awt.event.ActionEvent evt) {
        String[] cmd = {
            "/bin/bash",
            "-c",
            "ps -aux | egrep 'youtube-dl https|youtube-dl --max|youtube-dl --extract' | awk '{print $2}' | head -n 1 | xargs kill -9"
        };

            try {
                Process p1 = Runtime.getRuntime().exec(cmd);
                JOptionPane.showMessageDialog(null, "Has cancelado la Descarga", "INFORMACIÓN", 1);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se puede cancelar la Descarga", "ERROR", 0);
            }


    }

    
    
    
    public static void main(String args[]) {
        // Estilo de las ventanas
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                youtubedl init = new youtubedl();
                init.setVisible(true);
                init.setLocationRelativeTo(null);
            }
        });
    }
    
    // Variables                   
    private javax.swing.JMenu Archivo;
    private javax.swing.JButton Descargar;
    private javax.swing.JRadioButton EA;
    private javax.swing.JRadioButton MQ;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JButton Mostrar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JButton Detener;
    private javax.swing.JRadioButton NQ;
    private javax.swing.JLabel URL;
    private javax.swing.JMenuItem copiar1;
    private javax.swing.JMenuItem copiar2;
    private javax.swing.JLabel descargainfo;
    private javax.swing.JTextField directorio;
    private javax.swing.JPopupMenu editar1;
    private javax.swing.JPopupMenu editar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.ButtonGroup opciones;
    private javax.swing.JMenuItem pegar1;
    private javax.swing.JMenuItem pegar2;
    private javax.swing.JLabel progress;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTextField url;                  
}
