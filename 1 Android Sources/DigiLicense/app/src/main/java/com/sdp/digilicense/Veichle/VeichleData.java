package com.sdp.digilicense.Veichle;

public class VeichleData {

    String RegistrationNumber,VeichleId,VeichleBrand,VeichleType;


    public VeichleData(String registrationNumber, String veichleId, String veichleBrand, String veichleType) {
        RegistrationNumber = registrationNumber;
        VeichleId = veichleId;
        VeichleBrand = veichleBrand;
        VeichleType = veichleType;
    }


    public VeichleData() {

    }


    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        RegistrationNumber = registrationNumber;
    }

    public String getVeichleId() {
        return VeichleId;
    }

    public void setVeichleId(String veichleId) {
        VeichleId = veichleId;
    }

    public String getVeichleBrand() {
        return VeichleBrand;
    }

    public void setVeichleBrand(String veichleBrand) {
        VeichleBrand = veichleBrand;
    }

    public String getVeichleType() {
        return VeichleType;
    }

    public void setVeichleType(String veichleType) {
        VeichleType = veichleType;
    }
}
