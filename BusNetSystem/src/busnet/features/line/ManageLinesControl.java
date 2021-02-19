package busnet.features.line;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JTable;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import busnet.Application;
import busnet.DBInterface;
import busnet.entity.Bus;
import busnet.entity.Line;
import busnet.entity.Stop;
import busnet.guiElements.ConfirmationWindow;
import busnet.guiElements.ErrorWindow;
import busnet.guiElements.InfoWindow;

public class ManageLinesControl {
	private ManageLinesWindow mlWindow;
	private ArrayList<Stop> stopList;
	private ArrayList<Line> lineList;
	private ArrayList<Bus> busList;
	private LineDataWindow ldWindow;
	private StopDataWindow sdWindow;
	private BusDataWindow bdWindow;
	
	
	public ManageLinesControl() {
		stopList = rtrvStopList();
		lineList = rtrvLineList();
		busList = rtrvBusList();
		mlWindow = new ManageLinesWindow(lineList,stopList, busList) {
			@Override
			public void clickAddStopBtn() {
				sdWindow = new StopDataWindow() {
					@Override
					public void confirm() {
						saveStop();
					}
				};
				sdWindow.setVisible(true);
			}

			@Override
			public void clickDelStopBtn() {
				deleteStop();		
			}

			@Override
			public void clickAddLineBtn() {

				ldWindow = new LineDataWindow() {
					@Override
					public void confirm() {
						saveLine();
					}
				};
				ldWindow.setVisible(true);
			}

			@Override
			public void clickRemoveLineBtn() {
				deleteLine();
			}

			@Override
			public void clickUpdateLineBtn() {
				try {
					Line l = DBInterface.rtrvLineData(mlWindow.getMlPanel().getLList().getLineIdSelected());
					ldWindow = new LineDataWindow(l) {
						@Override
						public void confirm() {
							updateLine();
						}
					};
					ldWindow.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException e) {
					
				} catch (Exception e) {
					Application.showDbError(e);
				}
			}

			@Override
			public void clickAddBusBtn() {
				bdWindow = new BusDataWindow() {
					@Override
					public void confirm() {
						saveBus();
					}
				};
				bdWindow.setVisible(true);
			}

			@Override
			public void clickDelBusBtn() {
				deleteBus();
			}
			
		};
		mlWindow.setVisible(true);
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
	
	public ArrayList<Stop> rtrvStopList() {
		try {
			return DBInterface.rtrvStopList();
		} catch (Exception e) {
			Application.showDbError(e);
			return null;
		}
	}
	public ArrayList<Line> rtrvLineList() {
		try {
			return DBInterface.rtrvLineList();
		} catch (Exception e) {
			Application.showDbError(e);
			return null;
		}
	}
	public ArrayList<Bus> rtrvBusList() {
		try {
			return DBInterface.rtrvBusList();
		} catch (Exception e) {
			Application.showDbError(e);
			return null;
		}
	}
	
	public void saveStop() {
		try {
			Stop s = new Stop(getSdWindow().getStopIdField().getText(),getSdWindow().getStopNameField().getText());
			DBInterface.saveStopData(s);
			getSdWindow().dispose();
		} catch (Exception e) {
			Application.showDbError(e);
		} finally {
			updateStopTable();
		}
	}
	public void deleteStop() {
		try {
			String stopIdSelected = (String) mlWindow.getMsPanel().getTable().getModel().getValueAt(mlWindow.getMsPanel().getTable().getSelectedRow(), 0);
			ConfirmationWindow cfWindow = new ConfirmationWindow("Eliminazione fermata","Sei sicuro di voler eliminare questa fermata? L'operazione è irreversibile") {
				@Override
				public void yes() {
					try {
						DBInterface.removeStop(stopIdSelected);
						InfoWindow info = new InfoWindow("Fermata eliminata con successo");
						info.setVisible(true);
					} catch (MySQLIntegrityConstraintViolationException e) {
						ErrorWindow err = new ErrorWindow(e,"Impossibile eliminare la fermata:\nLa fermata: " + stopIdSelected + " è già instanzia in una o più linee. Rimuovi la fermata dalle linee in cui è instanziata e poi eliminala");
						err.setVisible(true);
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
		try {
			Bus bus = new Bus(bdWindow.getPlateField().getText(),(String)bdWindow.getTypeBox().getSelectedItem(),bdWindow.getModelField().getText(),bdWindow.getIsActiveBox().isEnabled());
			DBInterface.saveBusData(bus);
			bdWindow.dispose();
		} catch (MysqlDataTruncation e) {
			Application.showDbError(e);
		} catch (ClassNotFoundException e) {
			Application.showDbError(e);
		} catch (SQLException e) {
			Application.showDbError(e);
		} finally {
			updateBusTable();
		}
	}
	public void deleteBus() {
		try {
			String busSelected = (String)mlWindow.getMbPanel().getTable().getModel().getValueAt(mlWindow.getMbPanel().getTable().getSelectedRow(), 0);
			ConfirmationWindow cfWindow = new ConfirmationWindow("Conferma","Sei sicuro di voler rimuovere il veicolo dal garage? Questa azione è irreversibile") {
				@Override
				public void yes() {
					
					try {
						DBInterface.removeBus(busSelected);
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
		try {
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
			line.setTime(totTime+Application.pause);
			DBInterface.saveLineData(line);
			getLdWindow().dispose();
		} catch (Exception e) {
			Application.showDbError(e);
		} finally {
			updateLineTable();
		}
	}
	public void deleteLine() {
		try {
			String lineSelected = mlWindow.getMlPanel().getLList().getLineIdSelected();
			ConfirmationWindow cfWindow = new ConfirmationWindow("Conferma","Sei sicuro di voler rimuovere la linea? Questa azione è irreversibile.") {
				@Override
				public void yes(){
					try {
						DBInterface.removeLine(lineSelected);
						InfoWindow info = new InfoWindow("Eliminazione completata");
						info.setVisible(true);
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
			line.setTime(totTime+Application.pause);
			System.out.println(line);
			DBInterface.updateLineData(line);
			getLdWindow().dispose();
		} catch (MySQLIntegrityConstraintViolationException e) {
			ErrorWindow err = new ErrorWindow(e,"Impossibile aggiornare i dati inseriti. Uno o più campi non validi");
			err.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		} finally {
			updateLineTable();
		}
	}
	
	public void updateStopTable() {
		stopList = rtrvStopList();
		mlWindow.getMsPanel().setStopList(stopList);
		mlWindow.getMsPanel().loadTable();
	}
	public void updateLineTable() {
		lineList = rtrvLineList();
		mlWindow.getMlPanel().setLineList(lineList);
		mlWindow.getMlPanel().updateLineList();
	}
	public void updateBusTable() {
		busList = rtrvBusList();
		mlWindow.getMbPanel().setBusList(busList);
		mlWindow.getMbPanel().loadTable();
	}


}
