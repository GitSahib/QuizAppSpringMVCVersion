
package com.mems.domain;

import java.io.UnsupportedEncodingException;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Table(name = "user_profile")
public class Profile extends Model {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
    private String lastName;
    private String phoneNumber;
    private int studentId;
    private String entry;
    @Lob
    private byte[] image;
    @OneToOne
    private User user;
    //region -Getters and Setters
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Profile [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", user="
				+ user + "]";
	}
	/**
	 * @return the image
	 * @throws UnsupportedEncodingException 
	 */
	public String getImage() throws UnsupportedEncodingException {
		if(this.image == null)
		{
			return "";
		}
		byte[] encodeBase64 = Base64.encodeBase64(this.image);
		String base64Encoded = new String(encodeBase64, "UTF-8");
		
		return base64Encoded;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	/**
	 * @return the entry
	 */
	public String getEntry() {
		return entry;
	}
	/**
	 * @param entry the entry to set
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
   
	//endregion
	
}

