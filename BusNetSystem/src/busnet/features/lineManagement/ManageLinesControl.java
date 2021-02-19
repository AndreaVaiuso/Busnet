package busnet.features.lineManagement;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JTable;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import busnet.Application;
import busnet.ConfirmationWindow;
import busnet.ErrorWindow;
import busnet.InfoWindow;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Bus;
import busnet.entity.Line;
import busnet.entity.Stop;
import busnet.exception.FieldErrorException;

public class ManageLinesControl {
	private ManageLinesWindow mlWindow;
	private ArrayList<Stop> stopList;
	private ArrayList<Line> lineList;
	private ArrayList<Bus> busList;
	private LineDataWindow ldWindow;
	private StopDataWindow sdWindow;
	private BusDataWindow bdWindow;
	
	
	public ManageLinesControl() {
		try {
			stopList = DBInterface.rtrvStopList();
			lineList = DBInterface.rtrvLineList();
			busList = DBInterface.rtrvBusList();
			
			mlWindow = new ManageLinesWindow(lineList,stopList, busList) {
				@Override
				public void clickAddStopBtn() {
					saveStop();
				}

				@Override
				public void clickDelStopBtn() {
					deleteStop();		
				}

				@Override
				public void clickAddLineBtn() {
					saveLine();
				}

				@Override
				public void clickRemoveLineBtn() {
					deleteLine();
				}

				@Override
				public void clickUpdateLineBtn() {
					updateLine();
				}

				@Override
				public void clickAddBusBtn() {
					saveBus();
				}

				@Override
				public void clickDelBusBtn() {
					deleteBus();
				}
				
			};
			mlWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	public void saveStop() {
		sdWindow = new StopDataWindow() {
			@Override
			public void confirm() {
				try {
					if(getSdWindow().getStopIdField().getText().isEmpty() || getSdWindow().getStopNameField().getText().isEmpty()) {
						throw new FieldErrorException("Devi inserire i dati in tutti i campi");
					}
					Stop s = new Stop(getSdWindow().getStopIdField().getText(),getSdWindow().getStopNameField().getText());
					DBInterface.saveStopData(s);
					getSdWindow().dispose();
				} catch (com.mysql.jdbc.MysqlDataTruncation e) {
					Application.showError("Valori troppo lunghi per il database. Ricontrolla i form");
				}
				
				catch (FieldErrorException e) {
					Application.showError(e);
				} catch (Exception e) {
					Application.showDbError(e);
				} finally {
					updateStopTable();
				}
			}
		};
		sdWindow.setVisible(true);
		
	}
	public void deleteStop() {
		try {
			String stopIdSelected = (String) mlWindow.getMsPanel().getTable().getModel().getValueAt(mlWindow.getMsPanel().getTable().getSelectedRow(), 0);
			ConfirmationWindow cfWindow = new ConfirmationWindow("Eliminazione fermata","Sei sicuro di voler eliminare questa fermata? L'operazione è irreversibile") {
				@Override
				public void confirm() {
					try {
						DBInterface.removeStop(stopIdSelected);
						InfoWindow info = new InfoWindow("Fermata eliminata con successo");
						info.setVisible(true);
					} catch (MySQLIntegrityConstraintViolationException e) {
						Application.showError("Impossibile eliminare la fermata:\nLa fermata: " + stopIdSelected + " è già istanzia in una o più linee. Rimuovi la fermata dalle linee in cui è istanziata e poi eliminala");
					} catch (Exception e) {
						Application.showDbError(e);
					} finally {
						updateStopTable();
					}
				}
			};
			cfWindow.setVisible(true);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
	}
	
	public void saveBus() {
		bdWindow = new BusDataWindow() {
			@Override
			public void confirm() {
				try {
					if(bdWindow.getPlateField().getText().isEmpty() || bdWindow.getModelField().getText().isEmpty()) {
						throw new FieldErrorException("Devi inserire i dati in tutti i campi");
					}
					Bus bus = new Bus(bdWindow.getPlateField().getText(),(String)bdWindow.getTypeBox().getSelectedItem(),bdWindow.getModelField().getText(),bdWindow.getIsActiveBox().isEnabled());
					DBInterface.saveBusData(bus);
					bdWindow.dispose();
				} catch (FieldErrorException e) {
					Application.showError(e);
				} catch (MysqlDataTruncation e) {
					Application.showError("Uno o più campi di inserimento non validi");
				} catch (ClassNotFoundException e) {
					Application.showDbError(e);
				} catch (SQLException e) {
					Application.showDbError(e);
				} finally {
					updateBusTable();
				}
			}
		};
		bdWindow.setVisible(true);
	}
	
	public void deleteBus() {
		try {
			String busSelected = (String)mlWindow.getMbPanel().getTable().getModel().getValueAt(mlWindow.getMbPanel().getTable().getSelectedRow(), 0);
			ConfirmationWindow cfWindow = new ConfirmationWindow("Conferma","Sei sicuro di voler rimuovere il veicolo dal garage? Questa azione è irreversibile") {
				@Override
				public void confirm() {
					
					try {
						DBInterface.removeBus(busSelected);
					} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
						Application.showError("Attenzione. L'autobus " + busSelected + " è istanziato in delle corse e non può essere eliminato. Cancella le corse inerenti a questo autobus e riprova");
					} catch (ClassNotFoundException | SQLException e) {
						Application.showDbError(e);
					}
				}
			};
			cfWindow.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
		
		finally {
			updateBusTable();
		}
	}
	
	public void saveLine() {
		ldWindow = new LineDataWindow() {
			@Override
			public void confirm() {
				try {
					if(getLdWindow().getLineIdField().getText().isEmpty()) {
						throw new FieldErrorException("Devi inserire i dati in tutti i campi");
					}
					Line line = new Line();
					line.setLineId(getLdWindow().getLineIdField().getText());
					JTable table = getLdWindow().getTable();
					for(int i=1;i<table.getRowCount();i++) {
						Stop s = new Stop();
						s.setStopId((String)table.getModel().getValueAt(i, 0));
						s.setStopName((String)table.getModel().getValueAt(i, 1));
						Integer minutes = (Integer)table.getModel().getValueAt(i, 2);
						line.addStop(s, minutes);
					}
					int totTime = 0;
					for(int i=0;i<line.getTimeList().size();i++) {
						totTime+=line.getTimeList().get(i);
					}
					if(line.getStopList().size()<2) {
						Application.showError("Attenzione. Devi inserire almeno due fermate per creare una linea.\nFermate inserite: " + line.getStopList().size());
						return;
					}
					if((totTime+Application.pause)<180) {
						line.setTime(totTime+Application.pause);
						DBInterface.saveLineData(line);
						getLdWindow().dispose();
					} else Application.showError("Attenzione. La durata in minuti della corsa è troppo alta. Deve essere massimo 180 minuti (3 ore).\n" + (totTime+Application.pause) + " min");
					
				}
				catch (java.sql.BatchUpdateException e) {
					if (e.getCause() instanceof MySQLIntegrityConstraintViolationException)
						Application.showError("Fermata duplicata: Il percorso di una linea può contenere una fermata solo una volta.");
					else if(e.getCause() instanceof MysqlDataTruncation)
						Application.showError("Attenzione. Codice linea troppo lungo per il database");
					else Application.showDbError(e);
				}
				catch (Exception e) {
					Application.showDbError(e);
				} finally {
					updateLineTable();
				}
			}
		};
		ldWindow.setVisible(true);
	}
	
	public void deleteLine() {
		try {
			String lineSelected = mlWindow.getMlPanel().getLList().getLineIdSelected();
			ConfirmationWindow cfWindow = new ConfirmationWindow("Conferma","Sei sicuro di voler rimuovere la linea? Questa azione è irreversibile.") {
				@Override
				public void confirm(){
					try {
						DBInterface.removeLine(lineSelected);
						InfoWindow info = new InfoWindow("Eliminazione completata");
						info.setVisible(true);
					} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
						Application.showError("Attenzione, la linea " + lineSelected + " è istanziata in una o più corse. Cancella le corse istanziate e riprova");
					} catch (ClassNotFoundException e) {
						Application.showDbError(e);
					} catch (SQLException e) {
						Application.showDbError(e);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			cfWindow.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			
		}
		finally {
			updateLineTable();
		}
	}
	
	public void updateLine() {
		try {
			Line l = DBInterface.rtrvLineData(mlWindow.getMlPanel().getLList().getLineIdSelected());
			ldWindow = new LineDataWindow(l) {
				@Override
				public void confirm() {
					try {
						if(getLdWindow().getLineIdField().getText().isEmpty()) {
							throw new FieldErrorException("Dati inseriti non validi per il nome della linea");
						}
						Line line = new Line();
						line.setLineId(getLdWindow().getLineIdField().getText());
						JTable table = getLdWindow().getTable();
						for(int i=1;i<table.getRowCount();i++) {
							Stop s = new Stop();
							s.setStopId((String)table.getModel().getValueAt(i, 0));
							s.setStopName((String)table.getModel().getValueAt(i, 1));
							Integer minutes = (Integer)table.getModel().getValueAt(i, 2);
							line.addStop(s, minutes);
						}
						int totTime = 0;
						for(int i=0;i<line.getTimeList().size();i++) {
							totTime+=line.getTimeList().get(i);
						}
						if(line.getStopList().size()<2) {
							Application.showError("Attenzione. Devi inserire almeno due fermate per creare una linea.\nFermate inserite: " + line.getStopList().size());
							return;
						}
						if((totTime+Application.pause)<180) {
							line.setTime(totTime+Application.pause);
							DBInterface.updateLineData(line);
							getLdWindow().dispose();
						} else Application.showError("Attenzione. La durata in minuti della corsa è troppo alta. Deve essere massimo 180 minuti (3 ore).\n" + (totTime+Application.pause) + " min");
						
					} catch (MySQLIntegrityConstraintViolationException e) {
						Application.showError("Impossibile aggiornare i dati inseriti. Uno o più campi non validi");
					} 
					catch (java.sql.BatchUpdateException e) {
						if (e.getCause() instanceof MySQLIntegrityConstraintViolationException)
							Application.showError("Fermata duplicata: Il percorso di una linea può contenere una fermata solo una volta.");
						else if(e.getCause() instanceof MysqlDataTruncation)
							Application.showError("Attenzione. Codice linea troppo lungo per il database");
						else Application.showDbError(e);
					} catch (FieldErrorException e) {
						Application.showError(e);
					}
					catch (Exception e) {
						Application.showDbError(e);
					} finally {
						updateLineTable();
					}
				}
			};
			ldWindow.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}

	public void updateStopTable() {
		try{
			stopList = DBInterface.rtrvStopList();
			mlWindow.getMsPanel().setStopList(stopList);
			mlWindow.getMsPanel().loadTable();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	public void updateLineTable() {
		try {
			lineList = DBInterface.rtrvLineList();
			mlWindow.getMlPanel().setLineList(lineList);
			mlWindow.getMlPanel().updateLineList();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	public void updateBusTable() {
		try {
			busList = DBInterface.rtrvBusList();
			mlWindow.getMbPanel().setBusList(busList);
			mlWindow.getMbPanel().loadTable();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	public StopDataWindow getSdWindow() {
		return sdWindow;
	}

	public void setSdWindow(StopDataWindow sdWindow) {
		this.sdWindow = sdWindow;
	}

	public LineDataWindow getLdWindow() {
		return ldWindow;
	}

	public void setLdWindow(LineDataWindow ldWindow) {
		this.ldWindow = ldWindow;
	}


}
