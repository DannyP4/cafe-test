/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import model.Product;
import model.Cart;
import model.CartItem;



public class ProductItem extends javax.swing.JPanel {

    private Product product;
    private Cart cart;
    private Menu menuView;

    /**
     * Creates new form ProductItem
     */
    public ProductItem() {
        initComponents();
    }

    public ProductItem(Product product, Cart cart, Menu menuView) {
        this();
        this.product = product;
        this.cart = cart;
        this.menuView = menuView;

        lblProduct.setText(product.getName());
        lblCategory.setText("Category: " + product.getCategory().getName());
        lblPrice.setText("$ %.2f".formatted(product.getPrice()));
        jSpinner1.setValue(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblProduct = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        btnAddToCart = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        lblCategory = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setMaximumSize(new java.awt.Dimension(212, 186));
        setMinimumSize(new java.awt.Dimension(212, 186));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProduct.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProduct.setText("Name");
        add(lblProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 250, 20));

        lblPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice.setText("$ 0.00");
        add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 250, -1));

        btnAddToCart.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add to cart.png"))); // NOI18N
        btnAddToCart.setText("Add to cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 150, -1));

        jSpinner1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 90, -1));

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCategory.setText("Category");
        add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 250, 31));
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        if ((Integer) jSpinner1.getValue() <= 0) {
            jSpinner1.setValue(0);
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        int quantity = (Integer) jSpinner1.getValue();
        if (quantity < 1) {
            return;
        }
        jSpinner1.setValue(0);
        if (cart.getItems().stream().anyMatch(item -> item.getProduct().getName().equals(product.getName()))) {
            var cartItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getName().equals(product.getName()))
                    .toList().get(0);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            menuView.loadCart();
            return;
        }

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        cart.getItems().add(item);

        menuView.loadCart();
    }//GEN-LAST:event_btnAddToCartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProduct;
    // End of variables declaration//GEN-END:variables
}
