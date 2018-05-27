package bean;

/**
 * Created by apple on 2017/2/24.
 */
/**
 * id : 1
 * date : 1434297600000
 * workAddress : 杭州
 * salary : 8k-12k
 * property : 全职
 * duty : 独立设计android客户端框架，分解android客户端功能为独立模块，负责android客户端核心代码编写。
 * workYear : 2
 * education : 大专
 * nums : 若干
 * welfare : 周末双休,带薪年假,五险一金,年终分红,股票期权,节日福利
 * com : {"id":1,"userName":"coma","name":"阿里巴巴集团公司","category":"上市","site":"www.aalibaba.com","address":"杭州","scale":"25000","des":"阿里巴巴网络技术有限公司（简称：阿里巴巴集团）是由曾担任英语教师的马云为首的18人，于1999年在中国杭州创立。\n他们相信互联网能够创造公平的竞争环境，让小企业通过创新与科技扩展业务，并在参与国内或全球市场竞争时处于更有利的位置。"}
 */
public class Job {

    private int id;
    private long date;
    private String workAddress;
    private String salary;
    private String property;
    private String duty;
    private int wordYear;
    private String education;
    private String nums;
    private String welfare;
    private ComBean com;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", date=" + date +
                ", workAddress='" + workAddress + '\'' +
                ", salary='" + salary + '\'' +
                ", property='" + property + '\'' +
                ", duty='" + duty + '\'' +
                ", wordYear=" + wordYear +
                ", education='" + education + '\'' +
                ", nums='" + nums + '\'' +
                ", welfare='" + welfare + '\'' +
                ", com=" + com +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public int getWordYear() {
        return wordYear;
    }

    public void setWordYear(int wordYear) {
        this.wordYear = wordYear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public ComBean getCom() {
        return com;
    }

    public void setCom(ComBean com) {
        this.com = com;
    }

    /**
     * id : 1
     * userName : coma
     * name : 阿里巴巴集团公司
     * category : 上市
     * site : www.aalibaba.com
     * address : 杭州
     * scale : 25000
     * des : 阿里巴巴网络技术有限公司（简称：阿里巴巴集团）是由曾担任英语教师的马云为首的18人，于1999年在中国杭州创立。
     他们相信互联网能够创造公平的竞争环境，让小企业通过创新与科技扩展业务，并在参与国内或全球市场竞争时处于更有利的位置。
     private int id;
     private String userName;
     private String name;
     private String category;
     private String site;
     private String address;
     private String scale;
     private String des;
     */

    public static class ComBean{
        private int id;
        private String userName;
        private String name;
        private String category;
        private String site;
        private String address;
        private String scale;
        private String des;

        @Override
        public String toString() {
            return "ComBean{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    ", site='" + site + '\'' +
                    ", address='" + address + '\'' +
                    ", scale='" + scale + '\'' +
                    ", des='" + des + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}


