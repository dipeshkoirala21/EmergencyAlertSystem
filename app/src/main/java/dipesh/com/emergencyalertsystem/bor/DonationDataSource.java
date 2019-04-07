package dipesh.com.emergencyalertsystem.bor;




public interface DonationDataSource {
  void saveNewUser(String userId, User user);

  void saveReceiverDetails(String userId, ReceiverDonorRequestType receiverDonorRequestType);

  void saveDonorDetails(String userId, ReceiverDonorRequestType receiverDonorRequestType);
}
