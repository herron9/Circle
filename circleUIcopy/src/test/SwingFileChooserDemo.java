package test;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class SwingFileChooserDemo extends JPanel implements ActionListener {
  static private final String newline = "\n";

  JFileChooser fc;
  tempFilePath tempfilePath;

  public SwingFileChooserDemo(tempFilePath tempfilePath) {
    super(new BorderLayout());

    fc = new JFileChooser();
    this.tempfilePath=tempfilePath;
    int returnVal = fc.showOpenDialog(SwingFileChooserDemo.this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      this.tempfilePath.filePath = file.getPath();
    } else {

    }

  }

  

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

public static String chooseAFileFromCurrentMachine() {
	 tempFilePath temp = new tempFilePath();
	 JComponent newContentPane = new SwingFileChooserDemo(temp);
	 return temp.filePath;
}

}

class tempFilePath {
	String filePath;
}