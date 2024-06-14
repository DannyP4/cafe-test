/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.OpenPdf;
import common.Utils;
import dao.OrderDao;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.DeliveryInfo;
import model.Order;
import model.Staff;
import model.User;
import java.util.Objects;



public class ViewOrderDetails extends javax.swing.JFrame {

    /**
     * Creates new form Order_Details
     */
    public ViewOrderDetails() {
        initComponents();
    }

    private int orderId;

    public ViewOrderDetails(int orderId) {
        this();
        this.orderId = orderId;
    }

    private Order order;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblOrderedBy = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblRecipient = new javax.swing.JLabel();
        lblRecipientPhone = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblShipper = new javax.swing.JLabel();
        lblShipperPhone = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTotalCost = new javax.swing.JLabel();
        lblShipCost = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblFinalCost = new javax.swing.JLabel();
        btnInvoice = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOrderedBy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOrderedBy.setForeground(new java.awt.Color(255, 255, 255));
        lblOrderedBy.setText("Ordered by: ");
        getContentPane().add(lblOrderedBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 272, 20));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email: ");
        getContentPane().add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("Time: ");
        getContentPane().add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 272, -1));

        lblRecipient.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRecipient.setForeground(new java.awt.Color(255, 255, 255));
        lblRecipient.setText("Recipient: ");
        getContentPane().add(lblRecipient, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 272, -1));

        lblRecipientPhone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRecipientPhone.setForeground(new java.awt.Color(255, 255, 255));
        lblRecipientPhone.setText("Phone number: ");
        getContentPane().add(lblRecipientPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 272, -1));

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(255, 255, 255));
        lblAddress.setText("Address: ");
        getContentPane().add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 272, -1));

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus.setText("Status: ");
        getContentPane().add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 53, 272, -1));

        lblShipper.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblShipper.setForeground(new java.awt.Color(255, 255, 255));
        lblShipper.setText("Shipper: ");
        getContentPane().add(lblShipper, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 90, 272, -1));

        lblShipperPhone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblShipperPhone.setForeground(new java.awt.Color(255, 255, 255));
        lblShipperPhone.setText("Phone number: ");
        getContentPane().add(lblShipperPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 130, 272, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Items");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, -1, -1));

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Product", "Unit Price", "Quantity", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblItems);
        if (tblItems.getColumnModel().getColumnCount() > 0) {
            tblItems.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblItems.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 783, 240));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Total Cost:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 520, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ship Cost: ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 560, 81, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Discount: ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 600, 81, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Final Cost:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 640, 81, -1));

        lblTotalCost.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTotalCost.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        getContentPane().add(lblTotalCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, 120, 20));

        lblShipCost.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblShipCost.setForeground(new java.awt.Color(255, 255, 255));
        lblShipCost.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        getContentPane().add(lblShipCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, 120, 20));

        lblDiscount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDiscount.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        getContentPane().add(lblDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 120, 20));

        lblFinalCost.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblFinalCost.setForeground(new java.awt.Color(255, 255, 255));
        lblFinalCost.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        getContentPane().add(lblFinalCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, 120, 20));

        btnInvoice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/generate bill _ print.png"))); // NOI18N
        btnInvoice.setText("Export Invoice");
        btnInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoiceActionPerformed(evt);
            }
        });
        getContentPane().add(btnInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 680, -1, 41));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        jLabel3.setText("Order Details");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/full-page-background.PNG"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, -22, 1450, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        order = OrderDao.getInstance().getById(orderId);
        User user = order.getUser();
        DeliveryInfo deliveryInfo = order.getDeliveryInfo();
        Staff shipper = order.getShipper();
        lblOrderedBy.setText("Ordered by: " + user.getFullName());
        lblEmail.setText("Email: " + user.getEmail());
        lblTime.setText("Time: " + order.getCreatedAt().format(DateTimeFormatter.ofPattern("hh:mm, yyyy-MM-dd")));
        lblRecipient.setText("Recipient: " + deliveryInfo.getRecipientName());
        lblRecipientPhone.setText("Phone number: " + deliveryInfo.getPhoneNumber());
        lblAddress.setText("Address: " + deliveryInfo.getAddress());
        lblStatus.setText("Status: " + order.getStatus().getValue());
        lblShipper.setText("Shipper: " + (shipper == null ? "(Not yet assigned)" : shipper.getFullName()));
        if (shipper == null) {
            lblShipperPhone.setVisible(false);
        } else {
            lblShipperPhone.setText("Phone number: " + shipper.getPhoneNumber());
        }
        lblTotalCost.setText("$ %.2f".formatted(order.getTotalCost()));
        lblShipCost.setText("$ %.2f".formatted(order.getShipCost()));
        lblDiscount.setText("- $ %.2f".formatted(order.getDiscount()));
        lblFinalCost.setText("$ %.2f".formatted(order.getFinalCost()));

        DefaultTableModel tableModel = (DefaultTableModel) tblItems.getModel();
        order.getItems().forEach(item -> {
            tableModel.addRow(new Object[]{
                    tableModel.getRowCount() + 1,          // No.
                    item.getProduct().getName(),           // Product
                    item.getUnitPrice(),                   // Unit Price
                    item.getQuantity(),                    // Quantity
                    item.getTotalAmount(),                 // Subtotal
            });
        });

    }//GEN-LAST:event_formComponentShown

    private void btnInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoiceActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("bill-%d.pdf".formatted(orderId)));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
        fileChooser.setFileFilter(filter);

        String filePath;
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
//            System.out.println("Selected file path: " + filePath);
        } else {
            return;
        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();

            Paragraph cafeName = new Paragraph("                                                                    Cafe Shop\n");
            doc.add(cafeName);

            Paragraph starLine = new Paragraph("****************************************************************************************************************\n");
            doc.add(starLine);

            Paragraph info = new Paragraph();
            info.add(new Phrase("\tOrder ID: " + orderId + "\n"));
            info.add(new Phrase("\tCustomer name: " + order.getUser().getFullName() + "\n"));
            info.add(new Phrase("\tCreated at: " + Utils.formatTimestamp(order.getCreatedAt()) + "\n"));
            info.add(new Phrase("\tTotal cost: $ " + String.format("%.2f", order.getTotalCost()) + "\n"));
            info.add(new Phrase("\tShipping cost: $ " + String.format("%.2f", order.getShipCost()) + "\n"));
            info.add(new Phrase("\tDiscount: - $ " + String.format("%.2f", order.getDiscount()) + "\n"));
            info.add(new Phrase("\tFinal cost: $ " + String.format("%.2f", order.getFinalCost()) + "\n"));

            doc.add(info);
            doc.add(starLine);

            PdfPTable tb1 = new PdfPTable(6);
            tb1.addCell("No.");
            tb1.addCell("Product");
            tb1.addCell("Unit price");
            tb1.addCell("Quantity");
            tb1.addCell("Subtotal");
            tb1.addCell("Final cost");
            DefaultTableModel tableModel = (DefaultTableModel) tblItems.getModel();
            for (int i = 0; i < tblItems.getRowCount(); i++) {
                String no = String.valueOf(i + 1);
                String product = Objects.toString(tableModel.getValueAt(i, 1), "");
                String unitPrice = "$ " + Objects.toString(tableModel.getValueAt(i, 2), "");
                String quantity = Objects.toString(tableModel.getValueAt(i, 3), "");
                String subtotal = "$ " + Objects.toString(tableModel.getValueAt(i, 4), "");
                double itemFinalCost = Double.parseDouble(subtotal.replace("$", "")) + order.getShipCost() / tblItems.getRowCount() - order.getDiscount() / tblItems.getRowCount();
                String finalCost = "$ " + String.format("%.2f", itemFinalCost);

                tb1.addCell(no);
                tb1.addCell(product);
                tb1.addCell(unitPrice);
                tb1.addCell(quantity);
                tb1.addCell(subtotal);
                tb1.addCell(finalCost);
            }
            doc.add(tb1);
            doc.add(starLine);

            Paragraph thanksMsg = new Paragraph("Thanks for purchasing! Hope you visit again!");
            doc.add(thanksMsg);

            int ans = JOptionPane.showConfirmDialog(null, "Invoice saved to %s. Open now?".formatted(filePath));
            if (ans == JOptionPane.YES_OPTION) {
                OpenPdf.openByPath(filePath);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            doc.close();
        }
    }//GEN-LAST:event_btnInvoiceActionPerformed

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
            java.util.logging.Logger.getLogger(ViewOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewOrderDetails(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInvoice;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFinalCost;
    private javax.swing.JLabel lblOrderedBy;
    private javax.swing.JLabel lblRecipient;
    private javax.swing.JLabel lblRecipientPhone;
    private javax.swing.JLabel lblShipCost;
    private javax.swing.JLabel lblShipper;
    private javax.swing.JLabel lblShipperPhone;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotalCost;
    private javax.swing.JTable tblItems;
    // End of variables declaration//GEN-END:variables
}
