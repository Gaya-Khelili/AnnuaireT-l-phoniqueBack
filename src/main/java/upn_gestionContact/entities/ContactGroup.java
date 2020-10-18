package upn_gestionContact.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedQuery(name="ContactGroup.findAll", query="SELECT cG FROM ContactGroup cG")
public class ContactGroup implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7302793512149305504L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;
    private String groupName;

    @ManyToMany(mappedBy="contactGroups")
    private Set<Contact> contacts = new HashSet<>();


    public ContactGroup() {

    }
    public ContactGroup(String groupName) {

        this.groupName = groupName;

    }
    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }




    @Override
    public String toString() {
        return "ContactGroup [groupId=" + groupId + ", groupName=" + groupName  + "]";
    }

}
