package com.laioffer.job.entity;

public class ExampleJob {
    public String title;
    public int salary;
    public String starting;
    public boolean remote;
    public ExampleCoordinates coordinates;

    public ExampleJob(String title, int salary, String starting, boolean remote, ExampleCoordinates coordinates) {
        this.title = title;
        this.salary = salary;
        this.starting = starting;
        this.remote = remote;
        this.coordinates = coordinates;
    }

    public String toString() {
        return String.format("The job title is %s, with salary of %d per year, starting from %s.\n" +
                "The working from home option is: %s, and the coordinates is: %s", title, salary, starting, remote, coordinates);
    }
}
