package com.dragonnetwork.hihealth.cloudio;

public interface ComLayer {
    public void initCloud();
    public void SignUp(final String email, String password, final String first_name, final String last_name, final String location, final Boolean sex);
    public void Login(String email, String password);
    public void SignOut();
    public void RequestAppointmentDoc(String AptID);
    public void RequestMedicationDoc(String MedID);
    public void RequestReportDoc(String RepID);

}
