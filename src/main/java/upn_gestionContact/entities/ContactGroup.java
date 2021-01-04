package upn_gestionContact.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


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

    @ManyToMany(mappedBy = "contactGroups", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Contact> contacts = new HashSet<Contact>();

    public ContactGroup() {
    }
    public ContactGroup(String groupName) { this.groupName = groupName; }

    public long getGroupId() {
        return this.groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Contact> getContacts() {
        return this.contacts;
    }
    public void setContacts( Set<Contact> contacts) {
         this.contacts=contacts;
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.getContactGroups().add(this);
    }

    @Override
    public String toString() {
        return "ContactGroup [groupId=" + groupId + ", groupName=" + groupName  + "contact "+ contacts.stream().map(Contact::getidContact).collect(Collectors.toList()) + "]";
    }


}
