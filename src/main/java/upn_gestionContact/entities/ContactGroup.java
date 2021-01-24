package upn_gestionContact.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@NamedQuery(name="ContactGroup.findAll", query="SELECT cG FROM ContactGroup cG")
@NamedQuery(name="ContactGroup.search", query = "SELECT cG FROM ContactGroup cG WHERE cG.groupName LIKE :criteria")
public class ContactGroup implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7302793512149305504L;


    private String groupName;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @ManyToMany(mappedBy = "contactGroups")
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
        if (contacts.contains(contact))
            return ;
        contact.getContactGroups().add(this);
        contacts.add(contact);

    }
    public void  removeContact(Contact contact) {
        if (contacts.contains(contact))
            return ;
        contact.getContactGroups().remove(this);
        contacts.remove(contact);

    }

    @Override
    public String toString() {
        return "ContactGroup [groupId=" + groupId + ", groupName=" + groupName  + "contact "+ contacts.stream().map(Contact::getidContact).collect(Collectors.toList()) + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactGroup that = (ContactGroup) o;
        return groupId == that.groupId &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, groupId);
    }
}
