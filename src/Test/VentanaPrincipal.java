package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
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
		frame.setBounds(100, 100, 450, 139);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNewLabel = new JLabel("");
		
		JButton btnBuscarImagen = new JButton("Buscar Imagen");
		
		lblNewLabel_1 = new JLabel("Resultado: ?");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 35));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(lblNewLabel_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBuscarImagen)))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnBuscarImagen)))
					.addContainerGap(177, Short.MAX_VALUE))
		);
		
		btnBuscarImagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.dir")+"\\img\\patterns"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","gif","png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					lblNewLabel.setIcon(resizeImage(path));
					withoutLearning(path);
				}
			}
		});
		
		frame.getContentPane().setLayout(groupLayout);
	}
	private void withoutLearning(String path) {
		int size = 32;
		int npatt = 36;
		File[] characterFolder = new File(directory+"\\img\\patterns").listFiles();
		MultiLayerPerceptron net = MultiLayerPerceptron.load(directory+"\\resources\\red.txt");
		if(net == null) { System.out.println("Problemas con la red"); return;}
		
		double[] inputs = ImageProcessingBW.loadImage(path, size, size);
		double[] output = net.execute(inputs);

		int max = 0;
		for(int i = 0; i < npatt; i++)
			if(output[i] > output[max])
			{
				max = i;
			}
		
		System.out.println("El valor máximo es: "+String.format ("%.2f", (float)output[max] * 100)+"%. El caracter de la imagen corresponde a: "+characterFolder[max].getName());
		lblNewLabel_1.setText("Resultado: "+characterFolder[max].getName());
	}
	
	private ImageIcon resizeImage(String path) {
		ImageIcon image = new ImageIcon(path);
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imge = new ImageIcon(newImg);
		return imge;
	}
}
