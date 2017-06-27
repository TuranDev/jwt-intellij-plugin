package com.github.novotnyr.idea.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.JBUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class JwtPanel extends JPanel {
    private JLabel headerLabel = new JLabel("Header (algorithm and token type");

    private JTextArea headerTextArea = new JTextArea();

    private JLabel payloadLabel = new JLabel("Payload (data)");

    private JWTClaimsTableModel claimsTableModel;

    private JBTable claimsTable = new JBTable();

    private JLabel verifySignatureLabel = new JLabel("Verify Signature");

    private JTextField secretTextField = new JTextField();

    public JwtPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints cc = new GridBagConstraints();
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.weightx = 1;
        cc.weighty = 0;
        cc.anchor = GridBagConstraints.FIRST_LINE_START;
        cc.gridx = 0;
        cc.gridy = 0;
        cc.insets = JBUI.insets(5);
        add(this.headerLabel, cc);

        cc.gridy++;
        add(this.headerTextArea, cc);

        cc.gridy++;
        add(this.payloadLabel, cc);

        cc.gridy++;
        cc.weighty = 1;
        add(this.claimsTable, cc);

        /*
        cc.gridy++;
        cc.weighty = 0;
        add(this.verifySignatureLabel, cc);

        cc.gridy++;
        add(this.secretTextField, cc);
        */

        this.headerTextArea.setRows(3);
        this.headerTextArea.setText("{\n" +
                "  \"alg\": \"HS256\"\n" +
                "}");

    }

    public void setJwt(DecodedJWT jwt) {
        this.headerTextArea.setText(JwtHelper.prettyUnbase64Json(jwt.getHeader()));
        this.claimsTableModel = new JWTClaimsTableModel(jwt);
        this.claimsTable.setModel(this.claimsTableModel);
    }

    public String getSecret() {
        return this.secretTextField.getText();
    }

    public JTextField getSecretTextField() {
        return secretTextField;
    }
}
