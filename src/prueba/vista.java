/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.placeholder.PlaceHolder;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import java.awt.*; 
import sun.awt.*; 
import sun.awt.windows.*; 
import javax.swing.*; 
import java.awt.event.*;

//LAS CHIDAS

import com.sun.jna.Library;
import com.sun.jna.Native;

/*
/**
 *
 * @author carlo
 */


public class vista extends javax.swing.JFrame {
    String myString="";
    String image_name="";
    String pdf_name="";
    String pdf_name2="";
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    DocPrintJob job = null;

    

    /**
     * Creates new form vista
     */
    public vista() {
        initComponents();
        PlaceHolder holder = new PlaceHolder(entrada, "Ingresar el SKU");
        pap1.requestFocusInWindow();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setTitle("Impresion de etiquetas V1.1");
        
    }
    
    public interface TscLibDll extends Library {
        String path = new File("TSCLIB.dll").getAbsolutePath();
        TscLibDll INSTANCE = (TscLibDll) Native.loadLibrary(path, TscLibDll.class);
        int about ();
        int openport (String pirnterName);
        int closeport ();
        int sendcommand (String printerCommand);
        int setup (String width,String height,String speed,String density,String sensor,String vertical,String offset);
        int downloadpcx (String filename,String image_name);
        int barcode (String x,String y,String type,String height,String readable,String rotation,String narrow,String wide,String code);
        int printerfont (String x,String y,String fonttype,String rotation,String xmul,String ymul,String text);
        int clearbuffer ();
        int printlabel (String set, String copy);
        int formfeed ();
        int nobackfeed ();
        int windowsfont (int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, String content);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btn1 = new javax.swing.JButton();
        entrada = new javax.swing.JTextField();
        label = new javax.swing.JLabel();
        la_entrada = new javax.swing.JLabel();
        copies = new javax.swing.JTextField();
        pap1 = new javax.swing.JRadioButton();
        pap2 = new javax.swing.JRadioButton();
        label_size = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn1.setText("Imprimir");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 106, 172, 72));

        entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaActionPerformed(evt);
            }
        });
        entrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                entradaKeyTyped(evt);
            }
        });
        getContentPane().add(entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 31, 171, -1));
        getContentPane().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(559, 144, -1, 82));
        getContentPane().add(la_entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 106, 186, 21));

        copies.setText("1");
        getContentPane().add(copies, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 31, 46, -1));

        buttonGroup1.add(pap1);
        pap1.setSelected(true);
        pap1.setText("Etiqueta Corta");
        getContentPane().add(pap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 30, -1, -1));

        buttonGroup1.add(pap2);
        pap2.setText("Etiqueta Larga");
        getContentPane().add(pap2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 62, -1, -1));

        label_size.setText("Longitud: ");
        getContentPane().add(label_size, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 171, 28));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        String sku = entrada.getText();
        String copias = copies.getText();
        //int num_copies = Integer.parseInt(copias);
        /* Buscar la entrada via API*/
        String los_skus[] = new String[1];
        los_skus[0] = "QA2019";
        //for(int i=0; i<los_skus.length;i++){
            la_entrada.setText("Los SKUS de la Entrada: "+ sku);
            //res.append(los_skus[i]+"\n");
            //myString = los_skus[i];
            
            
            TscLibDll.INSTANCE.openport("TSC TE200");
            if(pap1.isSelected()){
                System.out.println("Papel corto");
                TscLibDll.INSTANCE.setup("50", "25", "6", "8", "0", "2", "2");  //Label height, width, etc.
            }else{
                System.out.println("Papel largo");
                TscLibDll.INSTANCE.setup("95", "25", "6", "8", "0", "2", "2");  //Label height, width, etc.
            }
            TscLibDll.INSTANCE.clearbuffer();
            TscLibDll.INSTANCE.barcode("24", "54", "128", "96", "1", "0", "2", "4", sku);
            TscLibDll.INSTANCE.printlabel("1", copias);
            TscLibDll.INSTANCE.closeport();
        //}   
        
       
        
    }//GEN-LAST:event_btn1ActionPerformed

    private void entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entradaActionPerformed

    private void entradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entradaKeyTyped
        // TODO add your handling code here:
        String hola = entrada.getText();
        int letras = hola.length() +1;
        //System.out.println("El tamaño es: "+ letras);
        label_size.setText("Longitud: "+letras);
        if(letras>=15){
            pap2.setSelected(true);
        }else{
            pap1.setSelected(true);
        }
        
        if(letras >30){
            btn1.setEnabled(false);
        }else{
            btn1.setEnabled(true);
        }
    }//GEN-LAST:event_entradaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField copies;
    private javax.swing.JTextField entrada;
    private javax.swing.JLabel la_entrada;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label_size;
    private javax.swing.JRadioButton pap1;
    private javax.swing.JRadioButton pap2;
    // End of variables declaration//GEN-END:variables
}
