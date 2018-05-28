package bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by apple on 2017/3/5.
 */

public class User implements Parcelable {
    private String name;
    private String sex;
    private int age;

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    /**
     * 从parcel中读取保持的数据
     * @param in
     */
    protected User(Parcel in) {
        name = in.readString();
        sex = in.readString();
        age = in.readInt();
    }


    public static final Creator<User> CREATOR = new Creator<User>() {

        // 当目标Activity从Intent中取出User对象时
        @Override
        public User createFromParcel(Parcel in) {
            Log.i("main", "createFromParcel()");
            return new User(in);
        }

        // 读取对象数组
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将User 的一个实例保存到Intent中，调用此方法
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i("main", "writeToParcel");
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return "User{"
                +"name:'"+name
                +"',sex"+sex
                +"', age"+age
                +"}";

    }
}
