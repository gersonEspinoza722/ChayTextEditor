import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class editor extends JFrame implements ActionListener {
    // Text component 
    JTextPane t;
    ArrayList<Integer> changed;

    // Frame 
    JFrame f;

    // Constructor 
    editor()
    {
        // Create a frame 
        f = new JFrame("editor");

        try {
            // Set metl look and feel 
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean 
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {
        }

        // Text component 
        t = new JTextPane();

        // Create a menubar 
        JMenuBar mb = new JMenuBar();

        // Create amenu for menu 
        JMenu m1 = new JMenu("File");

        // Create menu items 
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener 
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create amenu for menu 
        JMenu m2 = new JMenu("Edit");

        // Create menu items 
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");
        JMenuItem mi7 = new JMenuItem("color");
        // Add action listener 
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);
        JMenuItem mc = new JMenuItem("close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }

    // If a button is pressed 
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        if (s.equals("cut")) {
            t.cut();
        }
        else if (s.equals("copy")) {
            t.copy();
        }
        else if (s.equals("paste")) {
            t.paste();
        }
        else if (s.equals("color")) {
            //appendToPane(t,"hola",Color.RED);


            int selectionPos = t.getSelectionStart();
            WordObject w1 = new WordObject("hola", Color.green);
            WordObject w2 = new WordObject("mundo", Color.red);
            WordObject w3 = new WordObject("MIERDA", Color.CYAN);
            //System.out.println(t.getSelectedText());
            //System.out.println(t.getSelectionStart());

            String [] renglonesCompletos = t.getText().split("\n");
            ArrayList<String[]> renglonesTokenizados=new ArrayList<>();
            for (int i = 0; i < renglonesCompletos.length; i++){
                renglonesTokenizados.add(renglonesCompletos[i].split(" "));
            }
            ArrayList<ArrayList<WordObject>> words = new ArrayList<ArrayList<WordObject>>();

            for(int j =0; j<renglonesTokenizados.size();j++){
                ArrayList<WordObject> row = new ArrayList<>();
                for(int x = 0; x<renglonesTokenizados.get(j).length;x++){

                    System.out.println(renglonesTokenizados.get(j)[x]);
                    WordObject wordToken = new WordObject(renglonesTokenizados.get(j)[x],Color.BLACK);
                    //System.out.println(wordToken.getContent());
                    row.add(wordToken);
                    //System.out.println(row.size());
                }
                words.add(row);
                //System.out.println(words.size());
            }

            int wordCounter=0;

            t.setText("");
            System.out.println("selección: "+selectionPos);
            for(int r=0;r<words.size();r++){
                for(int w=0;w<words.get(r).size();w++){
                    System.out.println("contador: "+wordCounter);
                    if(wordCounter==selectionPos){
                        words.get(r).get(w).setColor(Color.red);
                    }
                    appendToPane(t,words.get(r).get(w).getContent(),words.get(r).get(w).getColor());
                    appendToPane(t," ",Color.BLACK);
                    wordCounter+=words.get(r).get(w).getContent().length();
                    if(words.get(r).size()>1 && w<words.get(r).size()-1){
                        wordCounter+=1;
                    }

                }
                appendToPane(t,"\n",Color.BLACK);
            }




            System.out.println("Renglones: " +renglonesTokenizados.size());
            System.out.println("Primer renglón tiene: "+renglonesTokenizados.get(0).length);


            //appendToPane(t,w1.getContent(),w1.getColor());
            //appendToPane(t,w2.getContent(),w2.getColor());
            //appendToPane(t,w3.getContent(),w3.getColor());
        }
        else if (s.equals("Save")) {
            // Create an object of JFileChooser class 
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write 
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write 
                    w.write(t.getText());

                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        }
        else if (s.equals("Print")) {
            try {
                // print the file 
                t.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if (s.equals("Open")) {
            // Create an object of JFileChooser class 
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog 
            int r = j.showOpenDialog(null);

            // If the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String 
                    String s1 = "", sl = "";

                    // File reader 
                    FileReader fr = new FileReader(fi);

                    // Buffered reader 
                    BufferedReader br = new BufferedReader(fr);

                    // Initilize sl 
                    sl = br.readLine();

                    // Take the input from the file 
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text 
                    t.setText(sl);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        }
        else if (s.equals("New")) {
            t.setText("");
        }
        else if (s.equals("close")) {
            f.setVisible(false);
        }
    }

    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    // Main class 
    public static void main(String args[])
    {
        editor e = new editor();
    }
} 