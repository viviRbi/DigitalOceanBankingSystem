package com.revature.models;

public class Transaction {
	
	private int id;
	private String create_at;
	private double transfer_amount;
	private int transaction_name_id;
	private int transfer_customer_id ;
	private int transfered_customer_id ;
	private int transfer_bank_rounting;  
	private int transfered_bank_rounting;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public double getTransfer_amount() {
		return transfer_amount;
	}
	public void setTransfer_amount(double transfer_amount) {
		this.transfer_amount = transfer_amount;
	}
	public int getTransaction_name_id() {
		return transaction_name_id;
	}
	public void setTransaction_name_id(int transaction_name_id) {
		this.transaction_name_id = transaction_name_id;
	}
	public int getTransfer_customer_id() {
		return transfer_customer_id;
	}
	public void setTransfer_customer_id(int transfer_customer_id) {
		this.transfer_customer_id = transfer_customer_id;
	}
	public int getTransfered_customer_id() {
		return transfered_customer_id;
	}
	public void setTransfered_customer_id(int transfered_customer_id) {
		this.transfered_customer_id = transfered_customer_id;
	}
	public int getTransfer_bank_rounting() {
		return transfer_bank_rounting;
	}
	public void setTransfer_bank_rounting(int transfer_bank_rounting) {
		this.transfer_bank_rounting = transfer_bank_rounting;
	}
	public int getTransfered_bank_rounting() {
		return transfered_bank_rounting;
	}
	public void setTransfered_bank_rounting(int transfered_bank_rounting) {
		this.transfered_bank_rounting = transfered_bank_rounting;
	}
	
	
}
