import com.revature.models.Customer;
import com.revature.models.User;

public interface UserServiceInterface {
	public User checkUser(String username, String password);
	public void depositTransferWithdraw(int transfer_bank_rounting, int transfer_account_number,int transfered_bank_rounting, int transfered_account_number, double amount);
	public void getBankAccountByRountingNumber(int someone_rounting_number);
	public Customer getCustomerInfoByAccountNumber(int id);
}
