import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Queue<Prova> provas = new LinkedList<>();
    private static ArrayList<Percurso> percursos = new ArrayList<>();
    private static ArrayList<Atleta> atletas = new ArrayList<>();
    private static ArrayList<Local> locais = new ArrayList<>();
    private static ArrayList<TipoProva> tiposProva = new ArrayList<>();
    private static final String DATA_FILE = "data.ser";

    public static void main(String[] args) {
            JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(1080,720);
        frame.setName("KSPORTS");
        frame.setVisible(true);
        frame.setBackground(Color.cyan);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(1080,720);
        mainPanel.setVisible(true);

        JButton btnSair = new JButton("X");
        btnSair.setBounds(1000,5,50,50);
        mainPanel.add(btnSair);

        JLabel lTitle = new JLabel("Maratonas KSPORTS");
        lTitle.setBounds(200,50,300,20);
        mainPanel.add(lTitle);

        JButton btnPercurso = new JButton("Novo percurso");
        btnPercurso.setBounds(25,500,150,50);
        mainPanel.add(btnPercurso);

        JButton btnAtleta = new JButton("Novo atleta");
        btnAtleta.setBounds(200,500,150,50);
        mainPanel.add(btnAtleta);

        JButton btnLocal = new JButton("Novo local");
        btnLocal.setBounds(375,500,150,50);
        mainPanel.add(btnLocal);

        JButton btnTipoProva = new JButton("Novo tipo prova");
        btnTipoProva.setBounds(550,500,150,50);
        mainPanel.add(btnTipoProva);

        JButton btnProva = new JButton("Nova prova");
        btnProva.setBounds(725,500,150,50);
        mainPanel.add(btnProva);

        JButton btnMostraProva = new JButton("Mostrar provas");
        btnMostraProva.setBounds(900,500,150,50);
        mainPanel.add(btnMostraProva);

        JButton btnAlterarSituacao = new JButton("Alterar Situação");
        btnAlterarSituacao.setBounds(50, 300, 150, 50);
        mainPanel.add(btnAlterarSituacao);

        JButton btnTempo = new JButton("Organizar / Tempo");
        btnTempo.setBounds(250, 300, 150, 50);
        mainPanel.add(btnTempo);

        JButton btnCarregar = new JButton("Carregar Dados");
        btnCarregar.setBounds(450, 300, 150, 50);
        mainPanel.add(btnCarregar);

        JButton btnDados = new JButton("Salvar Dados");
        btnDados.setBounds(650, 300, 150, 50);
        mainPanel.add(btnDados);

        btnAlterarSituacao.addActionListener(e -> {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080, 720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000, 5, 50, 50);
            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.setBounds(350, 500, 100, 50);
            JButton btnBuscar = new JButton("Buscar");
            btnBuscar.setBounds(200, 100, 100, 30);
            JButton btnPace = new JButton("Tempo Prova");
            btnPace.setBounds(500,500,100,50);
            panel.add(btnPace);

            JLabel title = new JLabel("Alterar Situação da Prova");
            title.setBounds(450, 25, 200, 50);
            panel.add(title);
            panel.add(btnExit);

            JLabel lCodigo = new JLabel("Código da Prova:");
            lCodigo.setBounds(100, 100, 100, 20);
            JTextField tCodigo = new JTextField();
            tCodigo.setBounds(100, 130, 100, 20);
            panel.add(lCodigo);
            panel.add(tCodigo);

            JLabel lDadosProva = new JLabel("Dados da Prova:");
            lDadosProva.setBounds(100, 170, 150, 20);
            panel.add(lDadosProva);

            JTextArea taDadosProva = new JTextArea();
            taDadosProva.setBounds(100, 200, 400, 150);
            taDadosProva.setEditable(false);
            panel.add(taDadosProva);

            JLabel lSituacaoAtual = new JLabel("Situação Atual:");
            lSituacaoAtual.setBounds(550, 170, 100, 20);
            panel.add(lSituacaoAtual);

            JLabel lblSituacaoAtual = new JLabel("");
            lblSituacaoAtual.setBounds(650, 170, 200, 20);
            panel.add(lblSituacaoAtual);

            JLabel lNovaSituacao = new JLabel("Nova Situação:");
            lNovaSituacao.setBounds(550, 200, 100, 20);
            panel.add(lNovaSituacao);

            JComboBox<SituacaoProva> cbSituacao = new JComboBox<>(SituacaoProva.values());
            cbSituacao.setBounds(650, 200, 200, 20);
            panel.add(cbSituacao);

            panel.add(btnBuscar);
            panel.add(btnSalvar);

            final Prova[] provaSelecionada = {null};
            btnBuscar.addActionListener(eBuscar -> {
                try {
                    int codigoProva = Integer.parseInt(tCodigo.getText());
                    provaSelecionada[0] = null;

                    for (Prova prova : provas) {
                        if (prova.getCodigo() == codigoProva) {
                            provaSelecionada[0] = prova;
                            break;
                        }
                    }
                    if (provaSelecionada[0] != null) {
                        StringBuilder dados = new StringBuilder();
                        dados.append("Código: ").append(provaSelecionada[0].getCodigo()).append("\n");
                        dados.append("Tipo: ").append(provaSelecionada[0].getTipoProva()).append("\n");
                        dados.append("Percurso: ").append(provaSelecionada[0].getPercurso().getNome()).append("\n");
                        dados.append("Local: ").append(provaSelecionada[0].getLocal().getNome()).append("\n");
                        dados.append("Atleta: ").append(provaSelecionada[0].getAtleta().getNome());
                        lblSituacaoAtual.setText(provaSelecionada[0].getSituacaoProva().getDescricao());
                        taDadosProva.setText(dados.toString());

                        if (provaSelecionada[0].getSituacaoProva() == SituacaoProva.FINALIZADA) {
                            cbSituacao.setEnabled(false);
                            JOptionPane.showMessageDialog(panel, "Prova finalizada não pode ser alterada");
                        } else {
                            cbSituacao.setEnabled(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Prova não encontrada");
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Código inválido");
                }
            });

            btnSalvar.addActionListener(eSalvar -> {
                if (provaSelecionada[0] == null) {
                    JOptionPane.showMessageDialog(panel, "Busque uma prova primeiro");
                    return;
                }

                try {
                    SituacaoProva novaSituacao = (SituacaoProva) cbSituacao.getSelectedItem();
                    provaSelecionada[0].setSituacaoProva(novaSituacao);
                    JOptionPane.showMessageDialog(panel, "Situação alterada com sucesso!");

                    lblSituacaoAtual.setText(provaSelecionada[0].getSituacaoProva().getDescricao());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Erro ao alterar situação");
                }
            });

            btnExit.addActionListener(exit -> {
                frame.setContentPane(mainPanel);
            });

            frame.setContentPane(panel);
        });

        frame.setContentPane(mainPanel);
        btnPercurso.addActionListener(e ->{
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080,720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000,5,50,50);
            JButton btnSave = new JButton("Salvar");
            btnSave.setBounds(350,500,100,50);
            JButton btnReset = new JButton("Reset");
            btnReset.setBounds(600,500,100,50);

            JLabel title = new JLabel("Cadastre novo Percurso");
            title.setBounds(450,25,400,50);
            panel.add(title);
            panel.add(btnExit);

            JLabel lNome = new JLabel("Nome: ");
            lNome.setBounds(100,100,100,20);
            JTextField tNome = new JTextField();
            tNome.setBounds(200,100,250,20);
            panel.add(lNome);
            panel.add(tNome);

            JLabel lDist = new JLabel("Distancia: ");
            lDist.setBounds(100,200,100,20);
            JTextField tDist = new JTextField();
            tDist.setBounds(200,200,250,20);
            panel.add(lDist);
            panel.add(tDist);

            JLabel lCidade = new JLabel("Cidade: ");
            lCidade.setBounds(100,300,100,20);
            JTextField tCidade = new JTextField();
            tCidade.setBounds(200,300,250,20);
            panel.add(lCidade);
            panel.add(tCidade);

            JLabel lCodigo = new JLabel("Codigo: ");
            lCodigo.setBounds(100,400,100,20);
            JTextField tCodigo = new JTextField();
            tCodigo.setBounds(200,400,250,20);
            panel.add(lCodigo);
            panel.add(tCodigo);

            panel.add(btnSave);
            panel.add(btnReset);
            btnSave.addActionListener(eSave->{
                try {
                    if (tNome.getText().isEmpty() || tCidade.getText().isEmpty() ||tDist.getText().isEmpty() || tCodigo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                        return;
                    }

                    int codigoPercurso = Integer.parseInt(tCodigo.getText());
                    if (provas.stream().anyMatch(prova -> prova.getCodigo() == codigoPercurso)) {
                        JOptionPane.showMessageDialog(null, "Já existe uma prova com este código!");
                        return;
                    }
                    Percurso p = new Percurso();
                    p.setCodigo(Integer.parseInt(tCodigo.getText()));
                    p.setCidade(tCidade.getText());
                    p.setNome(tNome.getText());
                    p.setDistancia(Integer.parseInt(tDist.getText()));
                    percursos.add(p);

                    JOptionPane.showMessageDialog(null,"Percuso cadastrado com sucesso");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERRO! Objeto inexistente / campo vazio");
                }
            });

            btnReset.addActionListener(eReset->{
                tCidade.setText("");
                tDist.setText("");
                tNome.setText("");
                tCodigo.setText("");
            });

            frame.setContentPane(panel);

            btnExit.addActionListener(exit->{
                frame.setContentPane(mainPanel);
            });
        });

        btnAtleta.addActionListener(e ->{
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080,720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000,5,50,50);
            JButton btnSave = new JButton("Salvar");
            btnSave.setBounds(350,500,100,50);
            JButton btnReset = new JButton("Reset");
            btnReset.setBounds(600,500,100,50);

            JLabel title = new JLabel("Cadastre novo Atleta");
            title.setBounds(100,25,400,50);
            panel.add(title);
            panel.add(btnExit);

            JLabel lNome = new JLabel("Nome: ");
            lNome.setBounds(100,100,100,20);
            JTextField tNome = new JTextField();
            tNome.setBounds(200,100,250,20);
            panel.add(lNome);
            panel.add(tNome);

            JLabel lFone = new JLabel("Telefone: ");
            lFone.setBounds(100,200,100,20);
            JTextField tFone = new JTextField();
            tFone.setBounds(200,200,250,20);
            panel.add(lFone);
            panel.add(tFone);

            JLabel lCodigo = new JLabel("Codigo: ");
            lCodigo.setBounds(100,300,100,20);
            JTextField tCodigo = new JTextField();
            tCodigo.setBounds(200,300,250,20);
            panel.add(lCodigo);
            panel.add(tCodigo);

            panel.add(btnSave);
            panel.add(btnReset);

            btnSave.addActionListener(eSave->{
                try {
                    if (tNome.getText().isEmpty() || tFone.getText().isEmpty() || tCodigo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                        return;
                    }

                    int codigoAtleta = Integer.parseInt(tCodigo.getText());
                    if (provas.stream().anyMatch(prova -> prova.getCodigo() == codigoAtleta)) {
                        JOptionPane.showMessageDialog(null, "Já existe uma prova com este código!");
                        return;
                    }
                    Atleta a = new Atleta();
                    a.setCodigo(Integer.parseInt(tCodigo.getText()));
                    a.setNome(tNome.getText());
                    a.setTelefone(Integer.parseInt(tFone.getText()));
                    atletas.add(a);
                    JOptionPane.showMessageDialog(null, "Atleta cadastrado com sucesso");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERRO! Objeto inexistente / campo vazio");
                }
            });
            btnReset.addActionListener(eReset->{
                tFone.setText("");
                tNome.setText("");
                tCodigo.setText("");
            });

            frame.setContentPane(panel);

            btnExit.addActionListener(exit->{
                frame.setContentPane(mainPanel);
            });
        });

        btnLocal.addActionListener(e->{
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080,720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000,5,50,50);
            JButton btnSave = new JButton("Salvar");
            btnSave.setBounds(350,500,100,50);
            JButton btnReset = new JButton("Reset");
            btnReset.setBounds(600,500,100,50);

            JLabel title = new JLabel("Cadastre novo Local");
            title.setBounds(100,25,400,50);
            panel.add(title);
            panel.add(btnExit);

            JLabel lNome = new JLabel("Nome: ");
            lNome.setBounds(100,100,100,20);
            JTextField tNome = new JTextField();
            tNome.setBounds(200,100,250,20);
            panel.add(lNome);
            panel.add(tNome);

            JLabel lCidade = new JLabel("Cidade: ");
            lCidade.setBounds(100,200,100,20);
            JTextField tCidade = new JTextField();
            tCidade.setBounds(200,200,250,20);
            panel.add(lCidade);
            panel.add(tCidade);

            JLabel lCodigo = new JLabel("Codigo: ");
            lCodigo.setBounds(100,300,100,20);
            JTextField tCodigo = new JTextField();
            tCodigo.setBounds(200,300,250,20);
            panel.add(lCodigo);
            panel.add(tCodigo);

            panel.add(btnSave);
            panel.add(btnReset);

            btnSave.addActionListener(eSave->{
                try {
                    if (tNome.getText().isEmpty() || tCidade.getText().isEmpty() || tCodigo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                        return;
                    }

                    int codigoLocal = Integer.parseInt(tCodigo.getText());
                    if (provas.stream().anyMatch(prova -> prova.getCodigo() == codigoLocal)) {
                        JOptionPane.showMessageDialog(null, "Já existe uma prova com este código!");
                        return;
                    }
                    Local l = new Local();
                    l.setCodigo(Integer.parseInt(tCodigo.getText()));
                    l.setNome(tNome.getText());
                    l.setCidade(tCidade.getText());
                    locais.add(l);
                    JOptionPane.showMessageDialog(null, "Local cadastrado com sucesso");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERRO! Campo vazio");
                }
            });
            btnReset.addActionListener(eReset->{
                tCidade.setText("");
                tNome.setText("");
                tCodigo.setText("");
            });

            frame.setContentPane(panel);

            btnExit.addActionListener(exit->{
                frame.setContentPane(mainPanel);
            });
        });

        btnTipoProva.addActionListener(e->{
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080,720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000,5,50,50);
            JButton btnSave = new JButton("Salvar");
            btnSave.setBounds(350,500,100,50);
            JButton btnReset = new JButton("Reset");
            btnReset.setBounds(600,500,100,50);

            JLabel title = new JLabel("Cadastre novo Tipo de Prova");
            title.setBounds(100,100,400,50);
            panel.add(title);
            panel.add(btnExit);

            JLabel lTipo = new JLabel("Tipo de Prova:");
            lTipo.setBounds(150,300,200,20);
            JComboBox<TipoProva> comboBox = new JComboBox<>(TipoProva.values());
            comboBox.setBounds(400,300,150,20);
            panel.add(lTipo);
            panel.add(comboBox);

            panel.add(btnSave);
            panel.add(btnReset);

            btnSave.addActionListener(eSave->{
                TipoProva selecionado = (TipoProva) comboBox.getSelectedItem();
                tiposProva.add(selecionado);
                JOptionPane.showMessageDialog(null,"Tipo de prova cadastrado com sucesso");
            });
            btnReset.addActionListener(eReset->{
                comboBox.setSelectedIndex(0);
            });

            frame.setContentPane(panel);

            btnExit.addActionListener(exit->{
                frame.setContentPane(mainPanel);
            });
        });

        btnProva.addActionListener(e ->{
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080,720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000,5,50,50);
            JButton btnSave = new JButton("Salvar");
            btnSave.setBounds(350,600,100,50);
            JButton btnReset = new JButton("Reset");
            btnReset.setBounds(600,600,100,50);

            JLabel title = new JLabel("Cadastre nova Prova");
            title.setBounds(450,25,400,50);
            panel.add(title);
            panel.add(btnExit);

            JLabel lTipo = new JLabel("Tipo de Prova:");
            lTipo.setBounds(100,200,200,20);
            JComboBox<TipoProva> comboBox = new JComboBox<>();
            for(TipoProva tipo : tiposProva) {
                comboBox.addItem(tipo);
            }
            comboBox.setBounds(200,200,150,20);
            panel.add(lTipo);
            panel.add(comboBox);

            JLabel lPercurso = new JLabel("Percurso: ");
            lPercurso.setBounds(100,100,100,20);
            JTextField tPercurso = new JTextField();
            tPercurso.setBounds(200,100,250,20);
            panel.add(lPercurso);
            panel.add(tPercurso);

            JLabel lAtleta = new JLabel("Atleta: ");
            lAtleta.setBounds(100,300,250,20);
            ButtonGroup grupoAtletas = new ButtonGroup();
            ArrayList<JRadioButton> radioButtons = new ArrayList<>();
            int xRadio = 150;

            for(Atleta a : atletas) {
                JRadioButton radio = new JRadioButton(a.getNome() + " (" + a.getCodigo() + ")");
                radio.setActionCommand(String.valueOf(a.getCodigo()));
                radio.setBounds(xRadio, 300, 75, 20);
                grupoAtletas.add(radio);
                panel.add(radio);
                radioButtons.add(radio);
                xRadio += 100;
            }
            panel.add(lAtleta);

            JLabel lLocal = new JLabel("Local: ");
            lLocal.setBounds(100,400,100,20);
            JTextField tLocal = new JTextField();
            tLocal.setBounds(200,400,250,20);
            panel.add(lLocal);
            panel.add(tLocal);

            JLabel lCodigo = new JLabel("Codigo: ");
            lCodigo.setBounds(100,500,100,20);
            JTextField tCodigo = new JTextField();
            tCodigo.setBounds(200,500,250,20);
            panel.add(lCodigo);
            panel.add(tCodigo);

            panel.add(btnSave);
            panel.add(btnReset);


            btnSave.addActionListener(eSave -> {
                try {
                    if (tLocal.getText().isEmpty() || tPercurso.getText().isEmpty() || tCodigo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                        return;
                    }

                    int codigoProva = Integer.parseInt(tCodigo.getText());
                    if (provas.stream().anyMatch(prova -> prova.getCodigo() == codigoProva)) {
                        JOptionPane.showMessageDialog(null, "Já existe uma prova com este código!");
                        return;
                    }

                    Local localSelecionado = null;
                    try {
                        int codigoLocal = Integer.parseInt(tLocal.getText());
                        localSelecionado = locais.stream()
                                .filter(l -> l.getCodigo() == codigoLocal)
                                .findFirst()
                                .orElse(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Código do local inválido!");
                        return;
                    }

                    if (localSelecionado == null) {
                        JOptionPane.showMessageDialog(null, "Local não encontrado!");
                        return;
                    }

                    Percurso percursoSelecionado = null;
                    try {
                        int codigoPercurso = Integer.parseInt(tPercurso.getText());
                        percursoSelecionado = percursos.stream()
                                .filter(p -> p.getCodigo() == codigoPercurso)
                                .findFirst()
                                .orElse(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Código do percurso inválido!");
                        return;
                    }

                    if (percursoSelecionado == null) {
                        JOptionPane.showMessageDialog(null, "Percurso não encontrado!");
                        return;
                    }

                    Atleta atletaSelecionado = null;
                    for (JRadioButton radio : radioButtons) {
                        if (radio.isSelected()) {
                            try {
                                int codigoAtleta = Integer.parseInt(radio.getActionCommand());
                                atletaSelecionado = atletas.stream()
                                        .filter(a -> a.getCodigo() == codigoAtleta)
                                        .findFirst()
                                        .orElse(null);
                                break;
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Código do atleta inválido!");
                                return;
                            }
                        }
                    }

                    if (atletaSelecionado == null) {
                        JOptionPane.showMessageDialog(null, "Selecione um atleta!");
                        return;
                    }

                    Prova p = new Prova();
                    p.setSituacaoProva(SituacaoProva.INSCRITA);
                    p.setTipoProva((TipoProva) comboBox.getSelectedItem());
                    p.setLocal(localSelecionado);
                    p.setPercurso(percursoSelecionado);
                    p.setAtleta(atletaSelecionado);
                    p.setCodigo(codigoProva);

                    provas.add(p);

                    JOptionPane.showMessageDialog(null,
                            "Prova cadastrada com sucesso!\n" +
                                    "Código: " + p.getCodigo() + "\n" +
                                    "Percurso: " + p.getPercurso().getNome() + "\n" +
                                    "Local: " + p.getLocal().getNome() + "\n" +
                                    "Atleta: " + p.getAtleta().getNome());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao cadastrar prova!\n" +
                                    "Detalhes: " + ex.getMessage());
                }
            });

            btnReset.addActionListener(eReset->{
                tLocal.setText("");
                tPercurso.setText("");
                comboBox.setSelectedIndex(0);
                tCodigo.setText("");

            });

            frame.setContentPane(panel);

            btnExit.addActionListener(exit->{
                frame.setContentPane(mainPanel);
            });
        });

        btnMostraProva.addActionListener(e->{
            if(percursos.size()!=0){
                for(Percurso p : percursos){
                    System.out.println(p.getCodigo());
                    System.out.println(p.getDistancia());
                    System.out.println(p.getCidade());
                    System.out.println(p.getNome());
                }
            }
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setSize(1080,720);

            JButton btnExit = new JButton("X");
            btnExit.setBounds(1000,5,50,50);
            JButton btnSave = new JButton("Salvar");
            btnSave.setBounds(350,600,100,50);
            JButton btnReset = new JButton("Reset");
            btnReset.setBounds(600,600,100,50);

            JLabel title = new JLabel("Consultar Provas");
            title.setBounds(100,100,200,50);
            panel.add(title);

            JLabel lCod = new JLabel("Codigo");
            lCod.setBounds(100,200,50,20);
            panel.add(lCod);

            JLabel lPerc = new JLabel("Percuso");
            lPerc.setBounds(200,200,50,20);
            panel.add(lPerc);

            JLabel lLocal = new JLabel("Local");
            lLocal.setBounds(300,200,50,20);
            panel.add(lLocal);

            JLabel lSituacao = new JLabel("Situacao");
            lSituacao.setBounds(400,200,50,20);
            panel.add(lSituacao);

            JLabel lAtlet = new JLabel("Atletas");
            lAtlet.setBounds(500,200,50,20);
            panel.add(lAtlet);

            int ylinha=250;
            for(Prova p : provas){
                JLabel perc = new JLabel(p.getPercurso().getNome());
                perc.setBounds(200,ylinha,50,20);
                panel.add(perc);

                JLabel cod = new JLabel(String.valueOf(p.getCodigo()));
                cod.setBounds(100,ylinha,50,20);
                panel.add(cod);

                JLabel local = new JLabel(p.getLocal().getNome());
                local.setBounds(300,ylinha,50,20);
                panel.add(local);

                JLabel situacao = new JLabel(p.getSituacaoProva().getDescricao());
                situacao.setBounds(400,ylinha,50,20);
                panel.add(situacao);

                JLabel atlet = new JLabel(p.getAtleta().getNome());
                atlet.setBounds(500,ylinha,50,20);
                panel.add(atlet);

                ylinha+=50;
            }


            panel.add(btnSave);
            panel.add(btnReset);
            panel.add(btnExit);

            frame.setContentPane(panel);

            btnExit.addActionListener(exit->{
                frame.setContentPane(mainPanel);
            });
        });

        btnTempo.addActionListener(e->{
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(1080, 720);

            JLabel lTitulo = new JLabel("Gerenciamento de Provas");
            JButton btnOrganizar = new JButton("Organizar Provas");
            JButton btnRegistrarTempo = new JButton("Registrar Tempo");
            JTextArea taLog = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(taLog);
            JButton btnExit = new JButton("Voltar");

            lTitulo.setBounds(400, 20, 250, 30);
            btnOrganizar.setBounds(100, 70, 200, 40);
            btnRegistrarTempo.setBounds(350, 70, 200, 40);
            scrollPane.setBounds(100, 130, 800, 400);
            btnExit.setBounds(400, 550, 150, 40);

            taLog.setEditable(false);
            Font font = new Font("Monospaced", Font.PLAIN, 12);
            taLog.setFont(font);

            panel.add(lTitulo);
            panel.add(btnOrganizar);
            panel.add(btnRegistrarTempo);
            panel.add(scrollPane);
            panel.add(btnExit);

            btnOrganizar.addActionListener(eOrganiza -> {
                if (provas.isEmpty()) {
                    taLog.append("Não há provas na fila para organizar.\n");
                    return;
                }

                taLog.append("--- PROCESSANDO FILA DE PROVAS ---\n");

                Queue<Prova> novaFila = new LinkedList<>();
                int emAndamento = 0, canceladas = 0;

                while (!provas.isEmpty()) {
                    Prova prova = provas.poll();

                    boolean podeIniciar = prova.getAtleta() != null &&
                            prova.getPercurso() != null &&
                            prova.getLocal() != null;

                    if (podeIniciar) {
                        if(prova.getSituacaoProva()==SituacaoProva.INSCRITA || prova.getSituacaoProva()==SituacaoProva. EM_ANDAMENTO) {
                            prova.setSituacaoProva(SituacaoProva.EM_ANDAMENTO);
                            emAndamento++;
                            taLog.append(String.format(
                                    "Prova %d - %s: EM ANDAMENTO (Atleta: %s, Percurso: %s)\n",
                                    prova.getCodigo(),
                                    prova.getTipoProva(),
                                    prova.getAtleta().getNome(),
                                    prova.getPercurso().getNome()
                            ));
                        }else if(prova.getSituacaoProva()==SituacaoProva.FINALIZADA){
                            taLog.append(String.format(
                                    "Prova %d - %s: FINALIZADA (Atleta: %s, Percurso: %s, Tempo: %d minutos)\n",
                                    prova.getCodigo(),
                                    prova.getTipoProva(),
                                    prova.getAtleta().getNome(),
                                    prova.getPercurso().getNome(),
                                    prova.getTempo()
                            ));
                        }else{
                            prova.setSituacaoProva(SituacaoProva.CANCELADA);
                            canceladas++;
                            taLog.append(String.format(
                                    "Prova %d - %s: CANCELADA (Faltam recursos)\n",
                                    prova.getCodigo(),
                                    prova.getTipoProva()
                            ));
                        }
                    } else {
                        prova.setSituacaoProva(SituacaoProva.CANCELADA);
                        canceladas++;
                        taLog.append(String.format(
                                "Prova %d - %s: CANCELADA (Faltam recursos)\n",
                                prova.getCodigo(),
                                prova.getTipoProva()
                        ));
                    }

                    novaFila.add(prova);
                }

                provas = novaFila;
                taLog.append(String.format(
                        "Resultado: %d provas em andamento, %d provas canceladas\n\n",
                        emAndamento, canceladas
                ));
            });

            btnRegistrarTempo.addActionListener(eRegistrar -> {
                if (provas.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Não há provas na fila para registrar tempo!");
                    return;
                }
                ArrayList<Prova> temp = new ArrayList<>();
                Queue<Prova> novoProvas = provas;
                for(int i=0;i<novoProvas.size();i++) {
                    Prova proximaProva = novoProvas.peek();
                    if(proximaProva.getSituacaoProva() == SituacaoProva.CANCELADA){
                        temp.add(proximaProva);
                    }
                    if (proximaProva.getSituacaoProva() == SituacaoProva.EM_ANDAMENTO) {
                        String mensagem = "Dados da Prova:\n\n" +
                                "Código: " + proximaProva.getCodigo() + "\n" +
                                "Tipo: " + proximaProva.getTipoProva() + "\n" +
                                "Percurso: " + proximaProva.getPercurso().getNome() +
                                " (" + proximaProva.getPercurso().getDistancia() + " km)\n" +
                                "Atleta: " + proximaProva.getAtleta().getNome() + "\n" +
                                "Situação: " + proximaProva.getSituacaoProva().getDescricao();

                        JPanel inputPanel = new JPanel();
                        JLabel label = new JLabel("Tempo final (minutos):");
                        JTextField tempoField = new JTextField(10);
                        inputPanel.add(label);
                        inputPanel.add(tempoField);

                        int result = JOptionPane.showConfirmDialog(
                                frame,
                                new Object[]{mensagem, inputPanel},
                                "Registrar Tempo da Prova",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                int tempo = Integer.parseInt(tempoField.getText());
                                if (tempo <= 0) {
                                    JOptionPane.showMessageDialog(frame, "O tempo deve ser maior que zero!");
                                    return;
                                }

                                proximaProva.setTempo(tempo);
                                proximaProva.setSituacaoProva(SituacaoProva.FINALIZADA);

                                novoProvas.poll();
                                novoProvas.add(proximaProva);

                                double pace = (double) tempo / proximaProva.getPercurso().getDistancia();

                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Tempo registrado com sucesso!\n" +
                                                "Pace médio: " + String.format("%.2f", pace) + " min/km",
                                        "Resultado",
                                        JOptionPane.INFORMATION_MESSAGE
                                );

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Por favor, digite um tempo válido (número inteiro)!");
                            }

                        }
                        return;
                    }
                    novoProvas.poll();
                    novoProvas.add(proximaProva);
                }
                novoProvas.addAll(temp);
                provas=novoProvas;
            });

            btnExit.addActionListener(eExit -> {
                frame.setContentPane(mainPanel);
                frame.revalidate();
            });

            frame.setContentPane(panel);
            frame.revalidate();
        });

        btnCarregar.addActionListener(e->{
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "Arquivo de dados não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Isso substituirá todos os dados atuais. Continuar?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                Data data = (Data) ois.readObject();

                provas = data.getProvas();
                percursos = data.getPercursos();
                atletas = data.getAtletas();
                locais = data.getLocais();
                tiposProva = data.getTiposProva();

                JOptionPane.showMessageDialog(frame, "Dados carregados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao carregar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDados.addActionListener(e->{
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                Data data = new Data(provas, percursos, atletas, locais, tiposProva);
                oos.writeObject(data);
                JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSair.addActionListener(e->{
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente sair?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });
    }
}