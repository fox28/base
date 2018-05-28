package cn.ucai.day17_06;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yao on 2016/12/25.
 */

public class Resume implements Parcelable {
    private String expireJob;
    private String workAddress;
    private String salary;
    private String property;

    public String getExpireJob() {
        return expireJob;
    }

    public void setExpireJob(String expireJob) {
        this.expireJob = expireJob;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Resume(String expireJob, String workAddress, String salary, String property) {
        this.expireJob = expireJob;
        this.workAddress = workAddress;
        this.salary = salary;
        this.property = property;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.expireJob);
        dest.writeString(this.workAddress);
        dest.writeString(this.salary);
        dest.writeString(this.property);
    }

    protected Resume(Parcel in) {
        this.expireJob = in.readString();
        this.workAddress = in.readString();
        this.salary = in.readString();
        this.property = in.readString();
    }

    public static final Parcelable.Creator<Resume> CREATOR = new Parcelable.Creator<Resume>() {
        @Override
        public Resume createFromParcel(Parcel source) {
            return new Resume(source);
        }

        @Override
        public Resume[] newArray(int size) {
            return new Resume[size];
        }
    };
}
