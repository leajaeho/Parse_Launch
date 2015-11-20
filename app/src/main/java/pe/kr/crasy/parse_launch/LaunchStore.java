package pe.kr.crasy.parse_launch;
import java.util.Date;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;
/**
 * Created by crasy on 15. 11. 12.
 */
public class LaunchStore extends RealmObject {
    @Required
    private Date date;
    private RealmList<LaunchList> LaunchList;
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
    public void setLaunchList(RealmList<LaunchList> launchList){
        this.LaunchList = launchList;
    }
    public RealmList getLaunchList(){
        return LaunchList;
    }
}

