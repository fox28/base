package cn.ucai.day06_05;

/**
 * Created by apple on 2017/2/20.
 */

public class App {
    private String name;
    private String version;
    private int fileSize;
    private String thumb;
    private String apk;
    private String intro;

    public App() {
    }

    public App(String name, String version, int fileSize) {
        this.setName(name);
        this.setVersion(version);
        this.setFileSize(fileSize);
    }

    public App(String name, String version, int fileSize, String thumb, String apk, String intro) {
        this.name = name;
        this.version = version;
        this.fileSize = fileSize;
        this.thumb = thumb;
        this.apk = apk;
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", fileSize=" + fileSize +
                ", thumb='" + thumb + '\'' +
                ", apk='" + apk + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
