package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Models.MultiLayerPerceptron;
import Utils.ImageProcessingBW;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VentanaPrincipal {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel1;
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel3;
	private JLabel lblNewLabel4;
	private JLabel lblNewLabel5;
	private JLabel lblNewLabel6;
	private JLabel lblNewLabel7;
	private JLabel lblNewLabelResultado1;
	private JLabel lblNewLabelResultado2;
	private JLabel lblNewLabelResultado3;
	private JLabel lblNewLabelResultado4;
	private JLabel lblNewLabelResultado5;
	private JLabel lblNewLabelResultado6;
	private JLabel lblNewLabelResultado7;
	private JLabel lblNewLabel_1;
	private static String directory = System.getProperty("user.dir");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNewLabel = new JLabel("");
		lblNewLabel1 = new JLabel("");
		lblNewLabel2 = new JLabel("");
		lblNewLabel3 = new JLabel("");
		lblNewLabel4 = new JLabel("");
		lblNewLabel5 = new JLabel("");
		lblNewLabel6 = new JLabel("");
		lblNewLabel7 = new JLabel("");
		
		lblNewLabelResultado1 = new JLabel("");
		lblNewLabelResultado2 = new JLabel("");
		lblNewLabelResultado3 = new JLabel("");
		lblNewLabelResultado4 = new JLabel("");
		lblNewLabelResultado5 = new JLabel("");
		lblNewLabelResultado6 = new JLabel("");
		lblNewLabelResultado7 = new JLabel("");
		
		lblNewLabelResultado1.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabelResultado2.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabelResultado3.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabelResultado4.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabelResultado5.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabelResultado6.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabelResultado7.setFont(new Font("Arial", Font.BOLD, 35));
		
		JButton btnBuscarImagen = new JButton("Buscar patente");
		
//		lblNewLabel_1 = new JLabel("Resultado: ?");
//		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 35));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
	 
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
	        .addComponent(btnBuscarImagen)
	        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	            .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
	            .addGroup(groupLayout.createSequentialGroup()
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                    .addComponent(lblNewLabel7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(lblNewLabelResultado7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
	    );
		
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
	            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	                .addComponent(btnBuscarImagen)
	                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
	            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	                .addGroup(groupLayout.createSequentialGroup()
	                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	                        .addComponent(lblNewLabel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabel2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabel3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabel4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabel5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabel6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabel7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	                        .addComponent(lblNewLabelResultado1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabelResultado2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabelResultado3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabelResultado4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabelResultado5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabelResultado6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(lblNewLabelResultado7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
	        );
		
		btnBuscarImagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.dir")+"\\img\\patternsTesting"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","gif","png");
				file.addChoosableFileFilter(filter);
				file.setMultiSelectionEnabled(true);
				int result = file.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = file.getSelectedFiles();
					lblNewLabel.setIcon(resizeImage(selectedFiles[0].getAbsolutePath(), lblNewLabel));
					
					lblNewLabel1.setIcon(resizeImage(selectedFiles[1].getAbsolutePath(), lblNewLabel1));
					lblNewLabel2.setIcon(resizeImage(selectedFiles[2].getAbsolutePath(), lblNewLabel2));
					lblNewLabel3.setIcon(resizeImage(selectedFiles[3].getAbsolutePath(), lblNewLabel3));
					lblNewLabel4.setIcon(resizeImage(selectedFiles[4].getAbsolutePath(), lblNewLabel4));
					lblNewLabel5.setIcon(resizeImage(selectedFiles[5].getAbsolutePath(), lblNewLabel5));
					lblNewLabel6.setIcon(resizeImage(selectedFiles[6].getAbsolutePath(), lblNewLabel6));
					lblNewLabel7.setIcon(resizeImage(selectedFiles[7].getAbsolutePath(), lblNewLabel7));
					
					lblNewLabelResultado1.setText(withoutLearning(selectedFiles[1].getAbsolutePath(), "Letras", 32, 49, 26));
					lblNewLabelResultado2.setText(withoutLearning(selectedFiles[2].getAbsolutePath(), "Letras", 32, 49, 26));
					lblNewLabelResultado3.setText(withoutLearning(selectedFiles[3].getAbsolutePath(), "Numeros", 32, 49, 10));
					lblNewLabelResultado4.setText(withoutLearning(selectedFiles[4].getAbsolutePath(), "Numeros", 32, 49, 10));
					lblNewLabelResultado5.setText(withoutLearning(selectedFiles[5].getAbsolutePath(), "Numeros", 32, 49, 10));
					lblNewLabelResultado6.setText(withoutLearning(selectedFiles[6].getAbsolutePath(), "Letras", 32, 49, 26));
					lblNewLabelResultado7.setText(withoutLearning(selectedFiles[7].getAbsolutePath(), "Letras", 32, 49, 26));
				}
			}
		});
		
		frame.getContentPane().setLayout(groupLayout);
	}
	private String withoutLearning(String path, String nombre, int sizex, int sizey, int npatt) {
//		int sizex = 32;
//		int sizey = 49;
//		int npatt = 10;
		File[] characterFolder = new File(directory+"\\img\\patterns"+nombre).listFiles();
		MultiLayerPerceptron net = MultiLayerPerceptron.load(directory+"\\resources\\red"+nombre+".txt");
		if(net == null) { System.out.println("Problemas con la red"); return "";}
		
		double[] inputs = ImageProcessingBW.loadImage(path, sizex, sizey);
		double[] output = net.execute(inputs);

		int max = 0;
		for(int i = 0; i < npatt; i++)
			if(output[i] > output[max])
			{
				max = i;
			}
		
		System.out.println("El valor máximo es: "+String.format ("%.2f", (float)output[max] * 100)+"%. El caracter de la imagen corresponde a: "+characterFolder[max].getName());
//		lblNewLabel_1.setText("Resultado: "+characterFolder[max].getName());
		return " "+characterFolder[max].getName();
	}
	
	private ImageIcon resizeImage(String path, JLabel label) {
		ImageIcon image = new ImageIcon(path);
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imge = new ImageIcon(newImg);
		return imge;
	}
}
