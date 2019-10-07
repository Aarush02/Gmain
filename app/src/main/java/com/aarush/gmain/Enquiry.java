package com.aarush.gmain;

public class Enquiry {
        private String userName;
        private String phoneNumber;
        private String email;
        private String goal;
        private String gimmingExperiance;
        private String address;

        public Enquiry(){

        }

        public Enquiry(String userName, String phoneNumber, String email, String goal, String gimmingExperiance, String address) {
            this.userName = userName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.goal = goal;
            this.gimmingExperiance = gimmingExperiance;
            this.address = address;
        }

        public String getUserName() {
            return userName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public String getGoal() {
            return goal;
        }

        public String getGimmingExperiance() {
            return gimmingExperiance;
        }

        public String getAddress() {
            return address;
        }
    }


