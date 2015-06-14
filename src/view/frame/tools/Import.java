package view.frame.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.ExceptionFace;
import exceptions.ExceptionPoint;
import exceptions.ExceptionSegment;
import model.loader.FileParser;
import BDD.Base;

@SuppressWarnings("serial")
public class Import extends JFrame {

	protected Base bdd;
	private JTextField tag, name;
	private JTextArea des;
	private File f;

	/**
	 * Cree un import pour les fichiers
	 */
	public Import() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Model 3D GTS", "gts");
		chooser.setFileFilter(filter);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
			if (f.exists()) {
				if (f.getName().lastIndexOf(".") > 0) {
					String ext = f.getName().substring(	f.getName().lastIndexOf("."));
					if (ext.equals(".gts")) {
						try {
							new FileParser(f.getPath());
							bdd = new Base("Base.db");
						JPanel panel = new JPanel();
						this.add(panel);
						this.setTitle("Import");
						this.setPreferredSize(new Dimension(250, 260));
						panel.add(new JLabel("Nom : "), BorderLayout.CENTER);
						name = new JTextField(f.getName().split(".gts")[0]);
						name.setPreferredSize(new Dimension(150, 25));
						panel.add(name, BorderLayout.CENTER);
						panel.add(new JLabel("Tags : "), BorderLayout.CENTER);
						tag = new JTextField();
						tag.setPreferredSize(new Dimension(150, 25));
						panel.add(tag, BorderLayout.CENTER);
						panel.add(new JLabel(
								"(3 tags maxi separer par des virgules ',')"),
								BorderLayout.CENTER);
						panel.add(new JLabel("Description : "),
								BorderLayout.CENTER);
						des = new JTextArea("");
						des.setLineWrap(true);
						des.setWrapStyleWord(true);
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setPreferredSize(new Dimension(150, 100));
						scrollPane.setViewportView(des);
						panel.add(scrollPane, BorderLayout.CENTER);
						JButton newFile = new JButton("New File");
						JButton cancel = new JButton("Cancel");
						JButton next = new JButton("Next");
						panel.add(cancel);
						panel.add(newFile);
						panel.add(next);
						this.pack();
						this.setLocationRelativeTo(null);
						this.setVisible(true);

						next.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String tags = tag.getText();
								String nam = name.getText();
								String desc = des.getText();
								String[] tab = tags.split(", ");
								ArrayList<String> tab2 = new ArrayList<String>();
								if (!bdd.estDeja(nam)) {
									File dest = new File("resources/models/"
											+ nam + ".gts");
									copyFile(f, dest);
									if (!des.equals("")) {
										tab2.add(nam);
										if (tab.length >= 3) {
											for (int i = 1; i < tab.length; i++) {
												tab2.add(tab[i - 1]);
											}
										} else if (tab.length < 3) {
											int i = 1;
											for (; i < tab.length + 1; i++) {
												tab2.add(tab[i - 1]);
											}
											for (; i < 4; i++) {
												tab2.add("null");
											}
										}
									} else {
										tab2.add(nam);
										for (int i = 1; i < 3; i++) {
											tab2.add("null");
										}
									}
									Scanner scan = null;
									try {
										scan = new Scanner(f);
									} catch (FileNotFoundException e1) {
										e1.printStackTrace();
									}
									int sommets = Integer.parseInt(scan.next());
									int segments = Integer.parseInt(scan.next());
									int faces = Integer.parseInt(scan.next());
									bdd.insert(nam, nam + ".gts", tab2.get(0),
											tab2.get(1), tab2.get(2),
											tab2.get(3), sommets, segments,
											faces, desc);
									dispose();
									JOptionPane
											.showMessageDialog(new JFrame(),
													"L'importation a ete effectuee avec succes");
								}

								else {
									JOptionPane.showMessageDialog(new JFrame(),
											"Le nom existe deja", "Warning",
									        JOptionPane.WARNING_MESSAGE);
								}

							}
						});
						cancel.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								dispose();

							}
						});
						newFile.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								int result = JOptionPane
										.showConfirmDialog(
												null,
												"Voulez-vous vraiment importer un fichier different ?",
												"Warning",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.QUESTION_MESSAGE);
								if (result == JOptionPane.YES_OPTION) {
									dispose();
									new Import();
								}
							}
						});
					} 
					 catch (ExceptionPoint e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (ExceptionSegment e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (ExceptionFace e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(),
								"Le fichier selectionne n'est pas de type gts", "Warning",
						        JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(),
						"Le fichier n'existe pas !", "Warning",
				        JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Copie le contenu d'un fichier (source) dans un autre fichier (dest)
	 * @param source le fichier source
	 * @param dest le fichier de destination
	 * @return True si la copie a marche
	 */
	public static boolean copyFile(File source, File dest) {
		try {
			// Declaration et ouverture des flux
			java.io.FileInputStream sourceFile = new java.io.FileInputStream(
					source);

			try {
				java.io.FileOutputStream destinationFile = null;

				try {
					destinationFile = new FileOutputStream(dest);

					// Lecture par segment de 0.5Mo
					byte buffer[] = new byte[512 * 1024];
					int nbLecture;

					while ((nbLecture = sourceFile.read(buffer)) != -1) {
						destinationFile.write(buffer, 0, nbLecture);
					}
				} finally {
					destinationFile.close();
				}
			} finally {
				sourceFile.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false; // Erreur
		}

		return true; // Resultat OK
	}
}
