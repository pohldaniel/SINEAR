package de.sinear.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
public class Action {

    @Id
    private String name;

    private Timestamp startDate;

    private Timestamp endDate;

    private boolean finishedPlanning;

    private boolean finished;

   
    public Action() {
    }
    
    
    public Action(String name, Timestamp startDate, Timestamp endDate, boolean finishedPlanning, boolean finished) {
    	
    	if (startDate.equals(endDate)) {
            throw new IllegalArgumentException("Die angegebene Start-Zeit ist gleich der Ende-Zeit");
        } else if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Die angegebene Ende-Zeit ist vor der Start-Zeit");
        }
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diffInDays > 10) {
            throw new IllegalArgumentException("Die Dauer der Aktion darf 10 Tage nicht überschreiten");
        }
    	
    	this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finishedPlanning = finishedPlanning;
        this.finished = finished;
    }

    public Action(Action newAction) {
        if (newAction.getStartDate().equals(newAction.getEndDate())) {
            throw new IllegalArgumentException("Die angegebene Start-Zeit ist gleich der Ende-Zeit");
        } else if (newAction.getStartDate().after(newAction.getEndDate())) {
            throw new IllegalArgumentException("Die angegebene Ende-Zeit ist vor der Start-Zeit");
        }
        long diffInMillies = Math.abs(newAction.getEndDate().getTime() - newAction.getStartDate().getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diffInDays > 10) {
            throw new IllegalArgumentException("Die Dauer der Aktion darf 10 Tage nicht überschreiten");
        }
        this.name = newAction.getName();
        this.startDate = newAction.getStartDate();
        this.endDate = newAction.getEndDate();
        this.finishedPlanning = newAction.isFinishedPlanning();
        this.finished = newAction.isFinished();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return finishedPlanning == action.finishedPlanning &&
                finished == action.finished &&
                name.equals(action.name) &&
                startDate.equals(action.startDate) &&
                endDate.equals(action.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate, finishedPlanning, finished);
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", finishedPlanning=" + finishedPlanning +
                ", finished=" + finished +
                '}';
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public boolean isFinishedPlanning() {
		return finishedPlanning;
	}

	public void setFinishedPlanning(boolean finishedPlanning) {
		this.finishedPlanning = finishedPlanning;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}	
    
}
