package pe.kr.crasy.parse_launch;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by crasy on 15. 11. 12.
 */
public class LaunchList extends RealmObject {
    @Required
    private String Launch;
    public void setLaunch(String launch){
        this.Launch = launch;
    }
    public String getLaunch(){
        return this.Launch;
    }
}
