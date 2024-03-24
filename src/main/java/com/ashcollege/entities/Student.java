package com.ashcollege.entities;


    public class Student {
        private String name;
        private String id;
        private String phone;

        public Student(String name, String id, String phone) {
            this.name = name;
            this.id = id;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getPhone() {
            return phone;
        }
    }


