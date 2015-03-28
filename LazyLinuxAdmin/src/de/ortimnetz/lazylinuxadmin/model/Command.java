package de.ortimnetz.lazylinuxadmin.model;


public abstract class Command implements ICommand {
	
	private int exitcode;
	private boolean successful;
	
	private Command(){
		successful = false;
		exitcode = -1;
	}
	
	@Override
	public void execute() {
		
	}

	public int getExitcode() {
		return exitcode;
	}

	public void setExitcode(int exitcode) {
		this.exitcode = exitcode;
	}

	public boolean isSuccessful() {
		if(exitcode == 0){
			successful = true;
		}
		
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	
	
	
}
