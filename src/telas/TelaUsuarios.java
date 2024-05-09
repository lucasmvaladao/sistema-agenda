package telas;

import java.sql.*;
import dal.ModuloConexao;
import javax.swing.JOptionPane;
import java.util.Set;
import java.util.HashSet;

public class TelaUsuarios extends javax.swing.JInternalFrame {
    
   Connection conexao = null;
   PreparedStatement pst = null;
   ResultSet rs = null;
    
    public TelaUsuarios() {
        initComponents();
        conexao = ModuloConexao.conector();
    } 
    
    private void consultar(){
    String sql = "SELECT * FROM tbl_cadastro WHERE id_cadastro=?";
    
    try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1,txtId.getText());
    rs = pst.executeQuery();
    if(rs.next()){
            txtNome.setText(rs.getString(2));
            txtEmail.setText(rs.getString(3));
            txtDDD.setText(rs.getString(4));
            txtCelular.setText(rs.getString(5));
            txtMes.setText(rs.getString(6));
            txtUF.setText(rs.getString(7));
            txtCidade.setText(rs.getString(8));
            txtEnd.setText(rs.getString(9));
            txtCPF.setText(rs.getString(10));
           
    } else{
                JOptionPane.showMessageDialog(null,"USUÁRIO NÃO CADASTRADO...");
                //As linhas abaixo limpam os campos do formulario
                        txtNome.setText(null);
                        txtEmail.setText(null);
                        txtDDD.setText(null);
                        txtCelular.setText(null);
                        txtMes.setText(null);
                        txtUF.setText(null);
                        txtCidade.setText(null);
                        txtEnd.setText(null);
                        txtCPF.setText(null);
                        txtId.setText(null);
    }
}
catch (Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
}
        private void adicionar() {
    // Verificar se todos os campos estão preenchidos
    if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtDDD.getText().isEmpty() || txtCelular.getText().isEmpty() || txtMes.getText().isEmpty() || txtUF.getText().isEmpty() || txtCidade.getText().isEmpty() || txtEnd.getText().isEmpty() || txtCPF.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
        return; // Sai do método sem realizar a inserção
    }
    
    // Validar mês de nascimento
    try {
        int mes = Integer.parseInt(txtMes.getText());
        if (mes < 1 || mes > 12) {
            JOptionPane.showMessageDialog(null, "Mês de nascimento inválido! Por favor, insira um número de 1 a 12.");
            return; // Sai do método sem realizar a inserção
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Mês de nascimento inválido! Por favor, insira um número de 1 a 12.");
        return; // Sai do método sem realizar a inserção
    }
    
    // Validar CPF
    if (!validarCPF(txtCPF.getText())) {
        JOptionPane.showMessageDialog(null, "CPF inválido! Por favor, insira um CPF válido.");
        return; // Sai do método sem realizar a inserção
    }
    
    // Verifica se a UF inserida é válida
    if (!validarUF(txtUF.getText())) {
        JOptionPane.showMessageDialog(null, "UF inválida");
        return; // Sai do método se a UF for inválida
    }
    
    // Verifica se o número de celular tem até 9 dígitos
    if (!validarCelular(txtCelular.getText())) {
        JOptionPane.showMessageDialog(null, "Número de celular inválido");
        return; // Sai do método se o número de celular for inválido
        }

    // Se todos os campos estiverem preenchidos e o CPF for válido, prossegue com a inserção no banco de dados
    String sql = "INSERT INTO tbl_cadastro (nome_cadastro, email_cadastro, ddd_cadastro, celular_cadastro, mes_cadastro, uf_cadastro, cid_cadastro, end_cadastro, cpf_cadastro) VALUES (?,?,?,?,?,?,?,?,?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtNome.getText());
        pst.setString(2, txtEmail.getText());
        pst.setString(3, txtDDD.getText());
        pst.setString(4, txtCelular.getText());
        pst.setString(5, txtMes.getText());
        pst.setString(6, txtUF.getText());
        pst.setString(7, txtCidade.getText());
        pst.setString(8, txtEnd.getText());
        pst.setString(9, txtCPF.getText());
        
        int adicionado = pst.executeUpdate();
        
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "USUÁRIO CADASTRADO!");
            // Limpar o formulário após adicionar
            txtId.setText(null);
            txtNome.setText(null);
            txtEmail.setText(null);
            txtDDD.setText(null);
            txtCelular.setText(null);
            txtMes.setText(null);
            txtUF.setText(null);
            txtCidade.setText(null);
            txtEnd.setText(null);
            txtCPF.setText(null);
        }    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
        private void alterar(){
    String sql = "UPDATE tbl_cadastro SET nome_cadastro=?, ddd_cadastro=?, celular_cadastro=?, email_cadastro=?, mes_cadastro=?, cpf_cadastro=?, end_cadastro=?, cid_cadastro=?, uf_cadastro=? WHERE id_cadastro=?";

    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtNome.getText());
        pst.setString(2, txtDDD.getText());
        pst.setString(3, txtCelular.getText());
        pst.setString(4, txtEmail.getText());
        pst.setString(5, txtMes.getText());
        pst.setString(6, txtCPF.getText());
        pst.setString(7, txtEnd.getText());
        pst.setString(8, txtCidade.getText());
        pst.setString(9, txtUF.getText());
        pst.setString(10, txtId.getText());

        int adicionado = pst.executeUpdate();

        if(adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            // Limpa o formulário após a atualização
            txtId.setText("");
            txtNome.setText("");
            txtEmail.setText("");
            txtDDD.setText("");
            txtCelular.setText("");
            txtMes.setText("");
            txtUF.setText("");
            txtCidade.setText("");
            txtEnd.setText("");
            txtCPF.setText("");

        } else {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar o usuário. Verifique os dados e tente novamente.");
        }
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário: " + e.getMessage());
    }
}
        private void apagar(){
        //a estrutura abaixo confirma a exclusão do cliente
        int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir este usuario?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM tbl_cadastro WHERE id_cadastro=?";
                try{
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtId.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0){
                        JOptionPane.showConfirmDialog(null, "USUÁRIO APAGADO");
                        txtNome.setText(null);
                        txtEmail.setText(null);
                        txtDDD.setText(null);
                        txtCelular.setText(null);
                        txtMes.setText(null);
                        txtUF.setText(null);
                        txtCidade.setText(null);
                        txtEnd.setText(null);
                        txtCPF.setText(null);
                    }
                    
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
        }
    }
        private boolean validarCelular(String celular) {
    // Verifica se o número de celular tem até 9 dígitos
    return celular.length() <= 9;
}
        private boolean validarUF(String uf) {
    // Inicializa o conjunto de UFs brasileiras manualmente
    HashSet<String> ufsBrasileiras = new HashSet<>();
    ufsBrasileiras.add("AC");
    ufsBrasileiras.add("AL");
    ufsBrasileiras.add("AP");
    ufsBrasileiras.add("AM");
    ufsBrasileiras.add("BA");
    ufsBrasileiras.add("CE");
    ufsBrasileiras.add("DF");
    ufsBrasileiras.add("ES");
    ufsBrasileiras.add("GO");
    ufsBrasileiras.add("MA");
    ufsBrasileiras.add("MT");
    ufsBrasileiras.add("MS");
    ufsBrasileiras.add("MG");
    ufsBrasileiras.add("PA");
    ufsBrasileiras.add("PB");
    ufsBrasileiras.add("PR");
    ufsBrasileiras.add("PE");
    ufsBrasileiras.add("PI");
    ufsBrasileiras.add("RJ");
    ufsBrasileiras.add("RN");
    ufsBrasileiras.add("RS");
    ufsBrasileiras.add("RO");
    ufsBrasileiras.add("RR");
    ufsBrasileiras.add("SC");
    ufsBrasileiras.add("SP");
    ufsBrasileiras.add("SE");
    ufsBrasileiras.add("TO");
 
    return ufsBrasileiras.contains(uf.toUpperCase());

}
        private boolean validarCPF(String cpf) {
    // Remove caracteres não numéricos do CPF
    cpf = cpf.replaceAll("[^0-9]", "");
    
    // Verifica se o CPF tem 11 dígitos
    if (cpf.length() != 11) {
        return false;
    }
    
    // Verifica se todos os dígitos são iguais, o que é inválido para um CPF válido
    int num1 = Character.getNumericValue(cpf.charAt(0));
    int num2 = Character.getNumericValue(cpf.charAt(1));
    int num3 = Character.getNumericValue(cpf.charAt(2));
    int num4 = Character.getNumericValue(cpf.charAt(3));
    int num5 = Character.getNumericValue(cpf.charAt(4));
    int num6 = Character.getNumericValue(cpf.charAt(5));
    int num7 = Character.getNumericValue(cpf.charAt(6));
    int num8 = Character.getNumericValue(cpf.charAt(7));
    int num9 = Character.getNumericValue(cpf.charAt(8));
    int num10 = Character.getNumericValue(cpf.charAt(9));
    int num11 = Character.getNumericValue(cpf.charAt(10));
    
    if (num1 == num2 && num2 == num3 && num3 == num4 && num4 == num5 &&
        num5 == num6 && num6 == num7 && num7 == num8 && num8 == num9 &&
        num9 == num10 && num10 == num11) {
        return false; // CPF inválido
    }
    
    // Calcula a soma dos produtos dos dígitos pelos pesos
    int soma1 = num1 * 10 + num2 * 9 + num3 * 8 + num4 * 7 + num5 * 6 + num6 * 5 + num7 * 4 + num8 * 3 + num9 * 2;
    int soma2 = num1 * 11 + num2 * 10 + num3 * 9 + num4 * 8 + num5 * 7 + num6 * 6 + num7 * 5 + num8 * 4 + num9 * 3 + num10 * 2;
    
    // Calcula os restos
    int resto1 = (soma1 * 10) % 11;
    int resto2 = (soma2 * 10) % 11;
    
    // Verifica se os restos correspondem aos dígitos verificadores
    if (resto1 == num10 && resto2 == num11) {
        return true; // CPF válido
    } else {
        return false; // CPF inválido
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnVisualizar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDDD = new javax.swing.JTextField();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnVisualizar1 = new javax.swing.JButton();
        btnAdicionar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        btnApagar1 = new javax.swing.JButton();
        txtId1 = new javax.swing.JTextField();
        txtNome1 = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtSenha2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSenha3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtUF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel1.setText("TELA DE CADASTRO");

        jLabel2.setText("ID");

        jLabel3.setText("Nome");

        jLabel4.setText("Email");

        jLabel5.setText("Celular");

        btnVisualizar.setText("Visualizar");
        btnVisualizar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnAdicionar.setText("Adicionar");
        btnAdicionar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnApagar.setText("Apagar");
        btnApagar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });

        jLabel6.setText("Endereço");

        jLabel7.setText("DDD");

        txtDDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDDDActionPerformed(evt);
            }
        });

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);

        jLabel8.setText("TELA DE CADASTRO");

        jLabel9.setText("ID");

        jLabel10.setText("Nome");

        jLabel11.setText("Email");

        jLabel12.setText("Celular");

        btnVisualizar1.setText("Visualizar");
        btnVisualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizar1ActionPerformed(evt);
            }
        });

        btnAdicionar1.setText("Adicionar");
        btnAdicionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar1ActionPerformed(evt);
            }
        });

        btnEditar1.setText("Editar");
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        btnApagar1.setText("Apagar");
        btnApagar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagar1ActionPerformed(evt);
            }
        });

        jLabel13.setText("Mês de Nascimento");

        jLabel14.setText("DDD");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVisualizar1)
                    .addComponent(btnApagar1))
                .addGap(21, 21, 21))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel8))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27)
                                .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(19, 19, 19)
                                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(btnAdicionar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar1))))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(49, 49, 49))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVisualizar1)
                    .addComponent(txtSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApagar1)
                    .addComponent(btnEditar1)
                    .addComponent(btnAdicionar1))
                .addGap(19, 19, 19))
        );

        jLabel15.setText("UF:");

        txtUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUFActionPerformed(evt);
            }
        });

        jLabel16.setText("Cidade:");

        jLabel17.setText("CPF");

        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        jLabel18.setText("jLabel18");

        jLabel19.setText("Mês Nascimento");

        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(104, 104, 104)
                                                .addComponent(jLabel19))
                                            .addComponent(jLabel16)
                                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 217, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 213, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel18))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 178, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 178, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
    consultar();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
    adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
    alterar();       
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
    apagar();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void txtDDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDDDActionPerformed

    private void btnVisualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVisualizar1ActionPerformed

    private void btnAdicionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionar1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnApagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnApagar1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void txtUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFActionPerformed

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionar1;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnApagar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JButton btnVisualizar1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtDDD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNome1;
    private javax.swing.JTextField txtSenha2;
    private javax.swing.JTextField txtSenha3;
    private javax.swing.JTextField txtUF;
    // End of variables declaration//GEN-END:variables
}