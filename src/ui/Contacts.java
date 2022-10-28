package ui;

import data.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



//Javaͼ���û�����
public class Contacts extends JFrame
{
    String URL = "jdbc:mysql://localhost:3306/sa";
    Connection con=null;
    JTabbedPane tabbedPane = new JTabbedPane(); //ѡ�
    JPanel panelA,panelB,panelC,panelD;  //ѡ��ϵ��������

    //���A�ϵĿؼ�
    JLabel lblCno,lblName,lblSex,lblBirth,lblPhone,lblEmail;
    JComboBox cboSex;
    JTextField txtCno,txtName,txtBirth,txtPhone,txtEmail;
    JButton btnInsertData,buttonCancel;

    //���B�ϵĿؼ�
    JLabel lblNoToDel;
    JTextField txtNoToDel;
    JButton btnNoToDel;

    //���C�ϵĿؼ�
    JLabel lblNameToFind;
    JTextField txtNameToFind;
    JButton btnNameToFind;
    JTextArea  areaShowResult;

    //���D�ϵĿؼ�
    JLabel lblCno1,lblName1,lblSex1,lblBirth1,lblPhone1,lblEmail1;
    JComboBox cboSex1;
    JTextField txtCno1,txtName1,txtBirth1,txtPhone1,txtEmail1;
    JButton btnChangeData;
    //���췽��
    public Contacts()
    {
        super("����ͨѶ¼ϵͳ(By 202031061367����)"); //���ô��ڱ���������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        panelA=new JPanel();
        panelB=new JPanel();
        panelC=new JPanel();
        panelD=new JPanel();
        tabbedPane.add("���",panelA);
        tabbedPane.add("ɾ��",panelB);
        tabbedPane.add("�鿴",panelC);
        tabbedPane.add("�޸�",panelD);

        //���÷����������������������
        addControlToPanelAdd();
        addControlToPanelDel();
        addControlToPanelQuery();
        addControlToPanelChange();


        //����һ����ǩ��������ʾ���֣������North
        JLabel labelTitle=new JLabel(" ����ͨѶ¼");
        labelTitle.setFont(new Font(null,Font.BOLD,36)); //��������
        labelTitle.setForeground( new Color(60,60,160) ); //������ɫ
        getContentPane().add("North",labelTitle);

        //���ѡ����������λ��
        getContentPane().add("Center",tabbedPane);

        //����ťע���¼�����
        MyButtonListener listen=new MyButtonListener();
        btnInsertData.addActionListener(listen);
        btnNoToDel.addActionListener(listen);
        btnNameToFind.addActionListener(listen);
        btnChangeData.addActionListener(listen);
    } // end of constructor


    //��ӿؼ������B��ɾ�����ݣ��ϵķ���
    public void   addControlToPanelDel()
    {
        lblNoToDel=new JLabel("������Ҫɾ����ID��");
        txtNoToDel=new JTextField("1" ,15);
        btnNoToDel=new JButton("ɾ��");
        panelB.add(lblNoToDel);
        panelB.add(txtNoToDel);
        panelB.add(btnNoToDel);
    }

    //��ӿؼ������C����ѯ���ݣ��ϵķ���
    public void   addControlToPanelQuery()
    {
        btnNameToFind=new JButton("ˢ��");
        areaShowResult=new JTextArea(20,80); //20�У�80��

        JPanel pTmp=new JPanel();
        pTmp.add(btnNameToFind);

        //���C���ñ߽粼�֣��Ϸ���������Ϣ������·�����ʾ��Ϣ���
        BorderLayout bl=new BorderLayout();
        panelC.setLayout(bl);

        //���C�Ŀ������pTmp���������areaShowResult
        panelC.add("North" ,pTmp);
        panelC.add("Center" ,areaShowResult);
    }

    //��ӿؼ������A�ϵķ���
    public void addControlToPanelAdd()
    {
//        lblCno=new JLabel("ѧ�� *  ");
        lblName=new JLabel("���� *  "); //��ǩ�������1���Ǻ������û������Ǳ���������ֶ�
        lblSex=new JLabel("�Ա� *  ");
        lblBirth=new JLabel("��������");
        lblPhone=new JLabel("�绰����");
        lblEmail=new JLabel("��������");

        txtCno=new JTextField("201731061234",12);
        txtName=new JTextField("����",10);
        String sex[]={"Ů","��"};cboSex=new JComboBox(sex);
        //Ϊ�˱��ڲ��ԣ��ı����и���һ��Ĭ������
        txtBirth=new JTextField("1998-09-18",10);
        txtPhone=new JTextField("028-83039876",15);
        txtEmail=new JTextField("zhangs@yahoo.com.cn",18);


        btnInsertData=new JButton("���");
        buttonCancel=new JButton("���");
        //�Ե�ǰ������ò��ֹ���
        GridBagLayout gbl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.anchor=GridBagConstraints.NORTHWEST;
        panelA.setLayout(gbl);

        //��ӿؼ� ��1��
        gbc.gridy=2;gbc.gridx=1;
        gbl.setConstraints(lblName,gbc);
        panelA.add(lblName);
        gbc.gridy=2;gbc.gridx=2;
        gbl.setConstraints(txtName,gbc);
        panelA.add(txtName);
        //��ӿؼ� ��2��
        gbc.gridy=3;gbc.gridx=1;
        gbl.setConstraints(lblSex,gbc);
        panelA.add(lblSex);
        gbc.gridy=3;gbc.gridx=2;
        gbl.setConstraints(cboSex,gbc);
        panelA.add(cboSex);
        //��ӿؼ� ��3��
        gbc.gridy=4;gbc.gridx=1;
        gbl.setConstraints(lblBirth,gbc);
        panelA.add(lblBirth);
        gbc.gridy=4;gbc.gridx=2;
        gbl.setConstraints(txtBirth,gbc);
        panelA.add(txtBirth);
        //��ӿؼ� ��4��
        gbc.gridy=5;gbc.gridx=1;
        gbl.setConstraints(lblPhone,gbc);
        panelA.add(lblPhone);
        gbc.gridy=5;gbc.gridx=2;
        gbl.setConstraints(txtPhone,gbc);
        panelA.add(txtPhone);
        //��ӿؼ� ��5��
        gbc.gridy=6;gbc.gridx=1;
        gbl.setConstraints(lblEmail,gbc);
        panelA.add(lblEmail);
        gbc.gridy=6;gbc.gridx=2;
        gbl.setConstraints(txtEmail,gbc);
        panelA.add(txtEmail);

        //��ӿؼ� ��6��
        gbc.gridy=7;gbc.gridx=3;
        gbl.setConstraints(btnInsertData,gbc);
        panelA.add(btnInsertData);
        gbc.gridy=7;gbc.gridx=4;
        gbl.setConstraints(buttonCancel,gbc);
        panelA.add(buttonCancel);
    }

    //��ӿؼ������A�ϵķ���
    public void addControlToPanelChange()
    {
        lblCno1=new JLabel("ID *  ");
        lblName1=new JLabel("���� *  "); //��ǩ�������1���Ǻ������û������Ǳ���������ֶ�
        lblSex1=new JLabel("�Ա� *  ");
        lblBirth1=new JLabel("��������");
        lblPhone1=new JLabel("�绰����");
        lblEmail1=new JLabel("��������");

        txtCno1=new JTextField("1",12);
        txtName1=new JTextField("����",10);
        String sex[]={"Ů","��"};cboSex1=new JComboBox(sex);
        //Ϊ�˱��ڲ��ԣ��ı����и���һ��Ĭ������
        txtBirth1=new JTextField("1998-09-18",10);
        txtPhone1=new JTextField("028-83039876",15);
        txtEmail1=new JTextField("zhangs@yahoo.com.cn",18);


        btnChangeData=new JButton("�޸�");
        //�Ե�ǰ������ò��ֹ���
        GridBagLayout gbl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.anchor=GridBagConstraints.NORTHWEST;
        panelD.setLayout(gbl);
        //��ӿؼ� ��1��
        gbc.gridy=1;gbc.gridx=1;
        gbl.setConstraints(lblCno1,gbc);
        panelD.add(lblCno1);
        gbc.gridy=1;gbc.gridx=2;
        gbl.setConstraints(txtCno1,gbc);
        panelD.add(txtCno1);
        //��ӿؼ� ��2��
        gbc.gridy=2;gbc.gridx=1;
        gbl.setConstraints(lblName1,gbc);
        panelD.add(lblName1);
        gbc.gridy=2;gbc.gridx=2;
        gbl.setConstraints(txtName1,gbc);
        panelD.add(txtName1);
        //��ӿؼ� ��3��
        gbc.gridy=3;gbc.gridx=1;
        gbl.setConstraints(lblSex1,gbc);
        panelD.add(lblSex1);
        gbc.gridy=3;gbc.gridx=2;
        gbl.setConstraints(cboSex1,gbc);
        panelD.add(cboSex1);
        //��ӿؼ� ��4��
        gbc.gridy=4;gbc.gridx=1;
        gbl.setConstraints(lblBirth1,gbc);
        panelD.add(lblBirth1);
        gbc.gridy=4;gbc.gridx=2;
        gbl.setConstraints(txtBirth1,gbc);
        panelD.add(txtBirth1);
        //��ӿؼ� ��5��
        gbc.gridy=5;gbc.gridx=1;
        gbl.setConstraints(lblPhone1,gbc);
        panelD.add(lblPhone1);
        gbc.gridy=5;gbc.gridx=2;
        gbl.setConstraints(txtPhone1,gbc);
        panelD.add(txtPhone1);
        //��ӿؼ� ��6��
        gbc.gridy=6;gbc.gridx=1;
        gbl.setConstraints(lblEmail1,gbc);
        panelD.add(lblEmail1);
        gbc.gridy=6;gbc.gridx=2;
        gbl.setConstraints(txtEmail1,gbc);
        panelD.add(txtEmail1);


        //��ӿؼ� ��7��
        gbc.gridy=7;gbc.gridx=3;
        gbl.setConstraints(btnChangeData,gbc);
        panelD.add(btnChangeData);
    }

    //��ʾ��ʾ��Ϣ�ķ���
    private void showMsg(String sArgs)
    {
        JOptionPane.showMessageDialog(Contacts.this,sArgs);
    }//end of showMsg


    class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            //�����¼�Դ�ǲ�ͬ��ť�����ò�ͬ�ķ���
            if (evt.getSource()==btnInsertData) insertData();
            if (evt.getSource()== btnNoToDel) deleteData();
            if (evt.getSource()==btnNameToFind) selectData();
            if (evt.getSource() == btnChangeData) changeData();
        }
    } // end of class MyButtonListener

    public static void main(String args[])
    {
        Contacts obj=new Contacts();
        obj.setBounds(640, 300, 640,480);
        obj.setVisible(true);
    }//end of main

    //������ݶ�Ӧ�Ĵ����װ������
    private void  insertData()
    {
//        String sNo=txtCno.getText();
        String sName=txtName.getText();
        String sSex=(String)cboSex.getSelectedItem();
        String sBirth=txtBirth.getText();
        String sPhone=txtPhone.getText();
        String sEmail=txtEmail.getText();

        String sSapce="    ";//�ĸ��ո�
        String sTmp=sSapce+ sName +sSapce+ sSex +sSapce+ sBirth +sSapce+ sPhone +sSapce+ sEmail;
        showMsg(sTmp);
        if (new DataAccess().insertStu(new Student(sName, sSex, sBirth, sPhone, sEmail)))
            showMsg("��ӳɹ���");
        else
            showMsg("����SQL�쳣�����ʧ�ܣ�");

    }//end of insertData()


    //ɾ�����ݶ�Ӧ�Ĵ����װ������
    private void  deleteData()
    {
        String sNo=txtNoToDel.getText();
        showMsg("��Ҫɾ����id�ǣ�"+sNo);
        int flag = new DataAccess().deleteStu(sNo);
        if (flag == -1)  showMsg("����SQL�쳣��ɾ��ʧ�ܣ�");
        else if (flag == 0) showMsg("��ϵ�˲����ڣ�ɾ��ʧ�ܣ�");
        else if (flag == 1) showMsg("ɾ���ɹ���");
    }//end of deleteData()

    //��ѯ���ݶ�Ӧ�Ĵ����װ������
    private void  selectData()
    {
        try{
            ResultSet rs = new DataAccess().selectStu();
            if(rs != null && rs.first()){
                rs.last();
                areaShowResult.setColumns(6);
                areaShowResult.setRows(rs.getRow());
                String[][] data=new String[rs.getRow()][6];rs.beforeFirst();
                int count=0;
                while(rs.next()){
                    data[count][0]=rs.getString("cNo");
                    data[count][1]=rs.getString("vName");
                    data[count][2]=rs.getString("cSex");
                    data[count][3]=rs.getString("dBirth");
                    data[count][4]=rs.getString("cPhone");
                    data[count][5]=rs.getString("vEmail");
                    count++;
                }
                areaShowResult.setText("");
                areaShowResult.append("ID"+"             "+"����"+"            "+"�Ա�"+"            "+
                        "��������"+"                      "+"�绰����"+"                     "+"��������");
                areaShowResult.append("\n");
                for(int i=0;i<data.length;i++){
                    for(int j=0;j<6;j++){
                        areaShowResult.append(data[i][j]);
                        areaShowResult.append("              ");
                    }
                    areaShowResult.append("\n");
                }
            }else{
                areaShowResult.setText("");
            }
        }catch(SQLException e){
            e.printStackTrace();
            showMsg("����SQL�쳣����ѯʧ�ܣ�");
        }
    }

    private void changeData() {
        String sNo=txtCno1.getText();
        String sName=txtName1.getText();
        String sSex=(String)cboSex1.getSelectedItem();
        String sBirth=txtBirth1.getText();
        String sPhone=txtPhone1.getText();
        String sEmail=txtEmail1.getText();

        try{
            if (new DataAccess().changeStu(new Student(Integer.parseInt(sNo), sName, sSex, sBirth, sPhone, sEmail)))   showMsg("�޸ĳɹ���");
            else showMsg("�޸ĳɹ���");
        }catch(SQLException e){
            e.printStackTrace();
            showMsg("����SQL�쳣�����ʧ�ܣ�");
        }
    }
}
