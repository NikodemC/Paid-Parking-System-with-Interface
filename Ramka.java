package parking;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Ramka extends JFrame {

	private JPanel contentPane;
	private JTextField rokWjazd;
	private JTextField miesiacWjazd;
	private JTextField dzienWjazd;
	private JTextField rokWyjazd;
	private JTextField miesiacWyjazd;
	private JTextField dzienWyjazd;
	private JTextField miejsce1;
	private JTextField miejsce2;
	private JTextField miejsce3;
	private JTextField miejsce4;
	private JTextField miejsce5;
	private JTextField miejsce6;
	private JTextField miejsce7;
	private JTextField miejsce8;
	private JTextField miejsce9;
	private JTextField miejsce10;
	static Parking parking;
	private JTextField godzinaWjazd;
	private JTextArea txtrGodzina;
	private JTextField godzinaWyjazd;
	private JTextField minuty1;
	private JTextField minuty2;
	private JPanel panelDostepnosc;
	private JPanel panelLista;
	private JPanel panelZarobione;
	private JTextArea txtrSumaPrzychodwParkingu;
	private JTextField statysykaRok;
	private JTextField statysykaMiesiac;
	private JTextField statysykaDzien;
	private JTextField statysykaRokDo;
	private JTextField statysykaMiesiacDo;
	private JTextField statysykaDzienDo;
	private JTextArea txtrDo;
	private JTextArea txtrOd;
	private JButton btnWyswietlParking;
	private JButton buttonCalyParkingWynik;
	private JButton btnWywietlDlaMiejsca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		parking = new Parking(10);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ramka frame = new Ramka();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ramka() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Parking System");
		setBounds(100, 100, 683, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelDaty = new JPanel();
		panelDaty.setBackground(Color.LIGHT_GRAY);
		panelDaty.setBorder(BorderFactory.createEtchedBorder());

		panelDostepnosc = new JPanel();
		panelDostepnosc.setBackground(Color.LIGHT_GRAY);
		panelDostepnosc.setBorder(BorderFactory.createEtchedBorder());

		panelLista = new JPanel();
		panelLista.setBackground(Color.LIGHT_GRAY);

		JComboBox nrMiejsc = new JComboBox();
		panelLista.add(nrMiejsc);
		nrMiejsc.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		nrMiejsc.setMaximumRowCount(3);

		JTextArea oknoKomunikat = new JTextArea();
		oknoKomunikat.setEditable(false);
		oknoKomunikat.setText("Okno do wy\u015Bwietlania komunikat\u00F3w");
		oknoKomunikat.setFont(new Font("Arial", Font.PLAIN, 13));
		oknoKomunikat.setBorder(BorderFactory.createLoweredBevelBorder());

		panelZarobione = new JPanel();
		panelZarobione.setBackground(Color.LIGHT_GRAY);
		panelZarobione.setBorder(BorderFactory.createEtchedBorder());

		buttonCalyParkingWynik = new JButton("Statystyki dla wszystkich miejsc");
		buttonCalyParkingWynik.setBackground(Color.WHITE);
		buttonCalyParkingWynik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<String> lista = parking.statysykaMiejsc();
			
				oknoKomunikat.setText("");
				for (int i = 0; i < lista.size(); i++) {
					oknoKomunikat.append(lista.get(i) + "\n");
				}

				String linijka = "Ca³kowite przychody parkingu z podzia³em na miejsca:";
				PrintWriter drukarz;
				try {
					drukarz = new PrintWriter(new FileWriter("Statysyka parking.txt"));
					drukarz.println(linijka);
					for (int i = 0; i < lista.size(); i++) {
						drukarz.println(lista.get(i));
					}
					drukarz.close();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(12).addComponent(panelZarobione,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(panelLista, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelDaty, 0, 0, Short.MAX_VALUE))
						.addComponent(panelDostepnosc, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(oknoKomunikat).addComponent(
								buttonCalyParkingWynik, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panelDostepnosc, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(12).addComponent(panelDaty,
										GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(33).addComponent(panelLista,
										GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addComponent(panelZarobione, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addComponent(oknoKomunikat, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE).addGap(18)
								.addComponent(buttonCalyParkingWynik).addGap(18)));

		txtrSumaPrzychodwParkingu = new JTextArea();
		txtrSumaPrzychodwParkingu.setForeground(Color.WHITE);
		txtrSumaPrzychodwParkingu.setText("Suma przychod\u00F3w w wybranym okresie czasu:");
		txtrSumaPrzychodwParkingu.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrSumaPrzychodwParkingu.setEditable(false);
		txtrSumaPrzychodwParkingu.setBackground(Color.BLACK);
		txtrSumaPrzychodwParkingu.setBorder(BorderFactory.createEtchedBorder());

		statysykaRok = new JTextField();
		statysykaRok.setText("2018");
		statysykaRok.setColumns(10);

		statysykaMiesiac = new JTextField();
		statysykaMiesiac.setText("1");
		statysykaMiesiac.setColumns(10);

		statysykaDzien = new JTextField();
		statysykaDzien.setText("01");
		statysykaDzien.setColumns(10);
		statysykaDzien.setBackground(Color.WHITE);

		statysykaRokDo = new JTextField();
		statysykaRokDo.setText("2018");
		statysykaRokDo.setColumns(10);

		statysykaMiesiacDo = new JTextField();
		statysykaMiesiacDo.setText("1");
		statysykaMiesiacDo.setColumns(10);

		statysykaDzienDo = new JTextField();
		statysykaDzienDo.setText("02");
		statysykaDzienDo.setColumns(10);
		statysykaDzienDo.setBackground(Color.WHITE);

		txtrDo = new JTextArea();
		txtrDo.setText("Do");
		txtrDo.setFont(new Font("Monospaced", Font.BOLD, 12));
		txtrDo.setEditable(false);
		txtrDo.setBackground(Color.LIGHT_GRAY);

		txtrOd = new JTextArea();
		txtrOd.setText("Od");
		txtrOd.setFont(new Font("Monospaced", Font.BOLD, 12));
		txtrOd.setEditable(false);
		txtrOd.setBackground(Color.LIGHT_GRAY);

		btnWyswietlParking = new JButton("Wy\u015Bwietl dla parkingu");
		btnWyswietlParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int zarobione = parking.zarobioneWTerminie(LocalDate.of(Integer.parseInt(statysykaRok.getText()),
						Integer.parseInt(statysykaMiesiac.getText()), Integer.parseInt(statysykaDzien.getText())),
						LocalDate.of(Integer.parseInt(statysykaRokDo.getText()),
								Integer.parseInt(statysykaMiesiacDo.getText()),
								Integer.parseInt(statysykaDzienDo.getText())));
				oknoKomunikat.setText("Parking zarobi³ w tym okresie " + zarobione + " z³.");
			}
		});

		btnWywietlDlaMiejsca = new JButton("Wy\u015Bwietl dla miejsca");
		btnWywietlDlaMiejsca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int zarobione = parking.zarobionePrzezMiejsceWTerminie(nrMiejsc.getSelectedIndex() + 1,
						LocalDate.of(Integer.parseInt(statysykaRok.getText()),
								Integer.parseInt(statysykaMiesiac.getText()),
								Integer.parseInt(statysykaDzien.getText())),
						LocalDate.of(Integer.parseInt(statysykaRokDo.getText()),
								Integer.parseInt(statysykaMiesiacDo.getText()),
								Integer.parseInt(statysykaDzienDo.getText())));

				oknoKomunikat.setText("Miejsce nr " + (nrMiejsc.getSelectedIndex() + 1) + " zarobi³o w tym okresie "
						+ zarobione + " z³.");
			}
		});

		GroupLayout gl_panelZarobione = new GroupLayout(panelZarobione);
		gl_panelZarobione
				.setHorizontalGroup(gl_panelZarobione.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrSumaPrzychodwParkingu, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
						.addGroup(gl_panelZarobione.createSequentialGroup().addContainerGap().addGroup(gl_panelZarobione
								.createParallelGroup(Alignment.TRAILING, false).addGroup(gl_panelZarobione
										.createSequentialGroup()
										.addComponent(txtrDo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(statysykaRokDo, GroupLayout.PREFERRED_SIZE, 37,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(statysykaMiesiacDo, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(statysykaDzienDo, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_panelZarobione.createSequentialGroup()
												.addComponent(txtrOd, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(statysykaRok, GroupLayout.PREFERRED_SIZE, 37,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(statysykaMiesiac, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(statysykaDzien, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)))
								.addGap(18)
								.addGroup(gl_panelZarobione.createParallelGroup(Alignment.LEADING)
										.addComponent(btnWyswietlParking, GroupLayout.DEFAULT_SIZE, 212,
												Short.MAX_VALUE)
										.addComponent(btnWywietlDlaMiejsca, GroupLayout.DEFAULT_SIZE, 212,
												Short.MAX_VALUE))
								.addContainerGap()));
		gl_panelZarobione
				.setVerticalGroup(
						gl_panelZarobione.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_panelZarobione.createSequentialGroup()
												.addComponent(txtrSumaPrzychodwParkingu, GroupLayout.PREFERRED_SIZE, 20,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panelZarobione.createParallelGroup(Alignment.BASELINE)
														.addComponent(statysykaMiesiac, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(statysykaDzien, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(btnWyswietlParking)
														.addComponent(statysykaRok, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtrOd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(19)
												.addGroup(gl_panelZarobione.createParallelGroup(Alignment.BASELINE)
														.addComponent(statysykaRokDo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(statysykaMiesiacDo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(statysykaDzienDo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtrDo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(btnWywietlDlaMiejsca))
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelZarobione.setLayout(gl_panelZarobione);

		JTextArea txtrMiejsce = new JTextArea();
		panelLista.add(txtrMiejsce);
		txtrMiejsce.setEditable(false);
		txtrMiejsce.setBackground(Color.LIGHT_GRAY);
		txtrMiejsce.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrMiejsce.setText("Miejsce");

		JTextArea txtrDostpnoMiejsc = new JTextArea();
		txtrDostpnoMiejsc.setLineWrap(true);
		txtrDostpnoMiejsc.setEditable(false);
		txtrDostpnoMiejsc.setForeground(Color.WHITE);
		txtrDostpnoMiejsc.setText(" Dost\u0119pno\u015B\u0107     miejsc");
		txtrDostpnoMiejsc.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txtrDostpnoMiejsc.setBackground(Color.BLACK);
		txtrDostpnoMiejsc.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.white));

		miejsce1 = new JTextField();
		miejsce1.setEditable(false);
		miejsce1.setText(" 1");
		miejsce1.setColumns(10);
		miejsce1.setBackground(Color.GREEN);

		miejsce2 = new JTextField();
		miejsce2.setEditable(false);
		miejsce2.setText(" 2");
		miejsce2.setColumns(10);
		miejsce2.setBackground(Color.GREEN);

		miejsce3 = new JTextField();
		miejsce3.setEditable(false);
		miejsce3.setText(" 3");
		miejsce3.setColumns(10);
		miejsce3.setBackground(Color.GREEN);

		miejsce4 = new JTextField();
		miejsce4.setEditable(false);
		miejsce4.setText(" 4");
		miejsce4.setColumns(10);
		miejsce4.setBackground(Color.GREEN);

		miejsce5 = new JTextField();
		miejsce5.setEditable(false);
		miejsce5.setText(" 5");
		miejsce5.setColumns(10);
		miejsce5.setBackground(Color.GREEN);

		miejsce6 = new JTextField();
		miejsce6.setEditable(false);
		miejsce6.setText(" 6");
		miejsce6.setColumns(10);
		miejsce6.setBackground(Color.GREEN);

		miejsce7 = new JTextField();
		miejsce7.setEditable(false);
		miejsce7.setText(" 7");
		miejsce7.setColumns(10);
		miejsce7.setBackground(Color.GREEN);

		miejsce8 = new JTextField();
		miejsce8.setEditable(false);
		miejsce8.setText(" 8");
		miejsce8.setColumns(10);
		miejsce8.setBackground(Color.GREEN);

		miejsce9 = new JTextField();
		miejsce9.setEditable(false);
		miejsce9.setText(" 9");
		miejsce9.setColumns(10);
		miejsce9.setBackground(Color.GREEN);

		miejsce10 = new JTextField();
		miejsce10.setEditable(false);
		miejsce10.setText("10");
		miejsce10.setColumns(10);
		miejsce10.setBackground(Color.GREEN);

		ArrayList<JTextField> listaTextMiejsce = new ArrayList<>();
		listaTextMiejsce.add(miejsce1);
		listaTextMiejsce.add(miejsce2);
		listaTextMiejsce.add(miejsce3);
		listaTextMiejsce.add(miejsce4);
		listaTextMiejsce.add(miejsce5);
		listaTextMiejsce.add(miejsce6);
		listaTextMiejsce.add(miejsce7);
		listaTextMiejsce.add(miejsce8);
		listaTextMiejsce.add(miejsce9);
		listaTextMiejsce.add(miejsce10);

		GroupLayout gl_panelDostepnosc = new GroupLayout(panelDostepnosc);
		gl_panelDostepnosc.setHorizontalGroup(gl_panelDostepnosc.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDostepnosc.createSequentialGroup()
						.addComponent(txtrDostpnoMiejsc, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(miejsce1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(miejsce2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(miejsce3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(miejsce4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(miejsce5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(miejsce6, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(miejsce7, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(miejsce8, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(miejsce9, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(miejsce10, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(93, Short.MAX_VALUE)));
		gl_panelDostepnosc.setVerticalGroup(gl_panelDostepnosc.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDostepnosc.createSequentialGroup()
						.addGroup(gl_panelDostepnosc.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDostepnosc.createSequentialGroup().addContainerGap()
										.addGroup(gl_panelDostepnosc.createParallelGroup(Alignment.LEADING)
												.addComponent(miejsce4, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(miejsce5, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelDostepnosc.createParallelGroup(Alignment.BASELINE)
														.addComponent(miejsce1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(miejsce2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(miejsce3, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(miejsce6, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(miejsce7, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(miejsce8, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(miejsce9, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(miejsce10, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(txtrDostpnoMiejsc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelDostepnosc.setLayout(gl_panelDostepnosc);

		JTextArea textArea = new JTextArea();
		textArea.setText("Data");
		textArea.setFont(new Font("Monospaced", Font.BOLD, 11));
		textArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);

		txtrGodzina = new JTextArea();
		txtrGodzina.setText(" Godzina");
		txtrGodzina.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrGodzina.setEditable(false);
		txtrGodzina.setBackground(Color.LIGHT_GRAY);

		rokWjazd = new JTextField();
		rokWjazd.setText("2018");
		rokWjazd.setColumns(10);

		miesiacWjazd = new JTextField();
		miesiacWjazd.setText("1");
		miesiacWjazd.setColumns(10);

		dzienWjazd = new JTextField();
		dzienWjazd.setBackground(Color.WHITE);
		dzienWjazd.setText("01");
		dzienWjazd.setColumns(10);

		rokWyjazd = new JTextField();
		rokWyjazd.setText("2018");
		rokWyjazd.setColumns(10);

		miesiacWyjazd = new JTextField();
		miesiacWyjazd.setText("1");
		miesiacWyjazd.setColumns(10);

		dzienWyjazd = new JTextField();
		dzienWyjazd.setText("02");
		dzienWyjazd.setColumns(10);

		godzinaWjazd = new JTextField();
		godzinaWjazd.setText("12");
		godzinaWjazd.setColumns(10);
		godzinaWjazd.setBackground(Color.WHITE);

		godzinaWyjazd = new JTextField();
		godzinaWyjazd.setText("20");
		godzinaWyjazd.setColumns(10);
		godzinaWyjazd.setBackground(Color.WHITE);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setText(":");
		textArea_1.setFont(new Font("Monospaced", Font.BOLD, 11));
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.LIGHT_GRAY);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setText(":");
		textArea_2.setFont(new Font("Monospaced", Font.BOLD, 11));
		textArea_2.setEditable(false);
		textArea_2.setBackground(Color.LIGHT_GRAY);

		minuty1 = new JTextField();
		minuty1.setEditable(false);
		minuty1.setText("00");
		minuty1.setColumns(10);
		minuty1.setBackground(Color.WHITE);

		minuty2 = new JTextField();
		minuty2.setEditable(false);
		minuty2.setText("00");
		minuty2.setColumns(10);
		minuty2.setBackground(Color.WHITE);

		JButton buttonZajmij = new JButton("Zajmij");
		buttonZajmij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (parking.getListaMiejsc().get(nrMiejsc.getSelectedIndex()).getStatus().equals(Status.Wolne)) {

					listaTextMiejsce.get(nrMiejsc.getSelectedIndex()).setBackground(Color.YELLOW);

					parking.zajmij(nrMiejsc.getSelectedIndex() + 1,
							LocalDate.of(Integer.parseInt(rokWjazd.getText()), Integer.parseInt(miesiacWjazd.getText()),
									Integer.parseInt(dzienWjazd.getText())),
							LocalTime.of(Integer.parseInt(godzinaWjazd.getText()), 00));

				} else {
					oknoKomunikat.setText("Niestety miejsce nr "
							+ parking.getListaMiejsc().get(nrMiejsc.getSelectedIndex()).getNrMiejsca()
							+ " jest zajête.");
				}
			}
		});

		JButton buttonZwolnij = new JButton("Zwolnij");
		buttonZwolnij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parking.getListaMiejsc().get(nrMiejsc.getSelectedIndex()).getStatus().equals(Status.Zajête)) {

					listaTextMiejsce.get(nrMiejsc.getSelectedIndex()).setBackground(Color.GREEN);

					parking.zwolnij(nrMiejsc.getSelectedIndex() + 1,
							LocalDate.of(Integer.parseInt(rokWyjazd.getText()),
									Integer.parseInt(miesiacWyjazd.getText()), Integer.parseInt(dzienWyjazd.getText())),
							LocalTime.of(Integer.parseInt(godzinaWyjazd.getText()), 00));

				} else {
					oknoKomunikat.setText(
							"Miejsce nr " + parking.getListaMiejsc().get(nrMiejsc.getSelectedIndex()).getNrMiejsca()
									+ " jest wolne!");
				}
			}
		});

		GroupLayout gl_panelDaty = new GroupLayout(panelDaty);
		gl_panelDaty.setHorizontalGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING).addGroup(gl_panelDaty
				.createSequentialGroup()
				.addGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDaty.createSequentialGroup()
								.addComponent(rokWyjazd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(miesiacWyjazd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(dzienWyjazd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDaty.createSequentialGroup()
								.addGroup(gl_panelDaty.createParallelGroup(Alignment.TRAILING)
										.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelDaty.createSequentialGroup()
												.addComponent(rokWjazd, GroupLayout.PREFERRED_SIZE, 37,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(miesiacWjazd,
														GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(dzienWjazd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
				.addGap(30)
				.addGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrGodzina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelDaty.createSequentialGroup().addGroup(gl_panelDaty
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDaty.createSequentialGroup()
										.addComponent(godzinaWyjazd, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2).addComponent(textArea_2, 0, 0, Short.MAX_VALUE))
								.addGroup(gl_panelDaty.createSequentialGroup()
										.addComponent(godzinaWjazd, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2).addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 6,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING, false)
										.addComponent(minuty1, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(minuty2, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING, false)
										.addComponent(buttonZajmij, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(buttonZwolnij, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addGap(30)))
				.addContainerGap()));
		gl_panelDaty.setVerticalGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDaty.createSequentialGroup()
						.addGroup(gl_panelDaty.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtrGodzina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelDaty.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panelDaty.createSequentialGroup().addComponent(buttonZajmij)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(buttonZwolnij))
								.addGroup(gl_panelDaty
										.createSequentialGroup().addGroup(
												gl_panelDaty.createParallelGroup(Alignment.BASELINE)
														.addComponent(rokWjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(miesiacWjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(dzienWjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(godzinaWjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
										.addGroup(gl_panelDaty.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panelDaty.createParallelGroup(Alignment.BASELINE)
														.addComponent(rokWyjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(miesiacWyjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(dzienWyjazd, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelDaty.createParallelGroup(Alignment.BASELINE)
														.addComponent(godzinaWyjazd, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 20,
																GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panelDaty.createSequentialGroup()
										.addComponent(minuty1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
										.addComponent(minuty2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		panelDaty.setLayout(gl_panelDaty);
		contentPane.setLayout(gl_contentPane);
	}
}
