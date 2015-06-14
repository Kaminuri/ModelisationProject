package view.frame.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BDD.Base;

public class Panel extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected Base bdd;
	protected JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7;
	protected JLabel points, segments, faces, des;
	protected JTextField recherche;
	protected JTextArea description;
	@SuppressWarnings("rawtypes")
	protected JList list;
	protected TreeMap<String, HashMap<String, String>> models;

	@SuppressWarnings("rawtypes")
	/**
	 * Cree les panels 
	 */
	public Panel() {
		this.setResizable(false);
		panel = new JPanel(false);
		panel2 = new JPanel(false);
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel(false);
		panel6 = new JPanel();
		panel7 = new JPanel();
		GridLayout grid = new GridLayout(2, 1);
		GridLayout grid1 = new GridLayout(1, 3);
		Container contentPane = this.getContentPane();
		this.setPreferredSize(new Dimension(500, 300));
		panel.setPreferredSize(new Dimension(110, 300));
		panel2.setPreferredSize(new Dimension(380, 300));
		panel3.setPreferredSize(new Dimension(380, 50));
		panel4.setPreferredSize(new Dimension(380, 50));
		panel5.setPreferredSize(new Dimension(350, 50));

		panel2.setLayout(new BorderLayout());
		panel2.add(panel3, BorderLayout.NORTH);
		panel2.add(panel5, BorderLayout.EAST);
		panel2.add(panel4, BorderLayout.SOUTH);

		panel5.setLayout(grid);
		panel6.setLayout(grid1);

		bdd = new Base("Base.db");
		list = new JList();
		modifieList("select", null);
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setPreferredSize(new Dimension(100, 250));
		panel.add(scrollPane1);
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(panel2, BorderLayout.EAST);

		points = new JLabel("Points : ");
		segments = new JLabel("Segments : ");
		faces = new JLabel("Faces : ");
		des = new JLabel("Description : ");
		description = new JTextArea();
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(150, 80));
		scrollPane.setViewportView(description);

		panel6.add(points, BorderLayout.CENTER);
		panel6.add(faces, BorderLayout.CENTER);
		panel6.add(segments, BorderLayout.CENTER);
		panel7.add(des, BorderLayout.CENTER);
		panel7.add(scrollPane, BorderLayout.CENTER);
		panel5.add(panel6);
		panel5.add(panel7);

		recherche = new JTextField();
		panel3.add(recherche, BorderLayout.WEST);
		recherche.setPreferredSize(new Dimension(200, 25));
		JButton find = new JButton("Rechercher");
		panel3.add(find, BorderLayout.EAST);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				list.setSelectedIndex(-1);
				String find = recherche.getText();
				if (find.indexOf(')') != -1 || find.indexOf('+') != -1
						|| find.indexOf('-') != -1 || find.indexOf('(') != -1) {
					JOptionPane
							.showMessageDialog(
									new JFrame(),
									"Il y a des caracteres interdit : +-() \nExemple de systaxe correcte : mot, mot2,");
				}
				System.out.println(find);
				if (find.equals("")) {
					modifieList("select", null);
				} else {
					modifieList("recherche", find);
				}
				validate();
			}
		};
		find.addActionListener(listener);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				if (list.getSelectedIndex() != -1) {
					JList list = ((JList) listSelectionEvent.getSource());
					String select = (String) list.getSelectedValue();
					points.setText("Points : "
							+ models.get(select).get("points"));
					segments.setText("Segments : "
							+ models.get(select).get("segments"));
					faces.setText("Faces : " + models.get(select).get("faces"));
					description.setText(models.get(select).get("Description"));
					validate();
				}
			}
		};
		list.addListSelectionListener(listSelectionListener);

		JButton cancel = new JButton("Annuler");
		ActionListener listenerCancel = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
				dispose();
			}
		};
		cancel.addActionListener(listenerCancel);
		panel4.add(cancel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@SuppressWarnings("unchecked")
	private void modifieList(String methode, String find) {
		if (methode.equals("select")) {
			models = bdd.select();
		} else if (methode.equals("recherche")) {
			String[] tab2 = find.split(", ");
			models = bdd.recherche(tab2);
		}
		int i = 0;
		String[] tab = new String[models.size()];
		for (String mapKey : models.keySet()) {
			tab[i] = models.get(mapKey).get("nom");
			i++;
		}
		list.setListData(tab);
	}
}
