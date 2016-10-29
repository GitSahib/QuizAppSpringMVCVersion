package com.mems.service.security.impl;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mems.domain.Profile;
import com.mems.domain.User;
import com.mems.repository.ProfileRepository;
import com.mems.service.bl.interfaces.StorageService;
import com.mems.service.security.interfaces.ProfileService;

@Transactional
@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	StorageService storageService;

	@Autowired
	private ProfileRepository profileRepository;

	public void setProfileRepository(ProfileRepository ProfileRepository) {
		this.profileRepository = ProfileRepository;
	}

	@Override
	public void save(Profile Profile) {
		profileRepository.save(Profile);
	}

	@Override
	public Profile findByFirstName(String FirstName) {
		return profileRepository.findByFirstName(FirstName);
	}

	@Override
	public void updateUserProfile(User user, Profile profileForm) {
		try {
			// hear i am trying to updating the charge_codes
			Profile profile = profileRepository.findByUser(user);
			profile.setFirstName(profileForm.getFirstName());
			profile.setLastName(profileForm.getLastName());
			profile.setPhoneNumber(profileForm.getPhoneNumber());
			profileRepository.saveAndFlush(profile);
		} catch (Exception ex) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mems.service.security.interfaces.ProfileService#saveImage(org.
	 * springframework.web.multipart.MultipartFile, com.mems.domain.User,
	 * org.springframework.core.env.Environment)
	 */
	@Override
	public ResponseEntity<String> saveImage(MultipartFile uploadfile, User user) {
		try {
			// Get the filename and build the local file path
			//String filename = storageService.store(uploadfile, user);
			Profile profile = user.getProfile();
			Image image = ImageIO.read(uploadfile.getInputStream());
			byte[] bytes = resizeImage( image, 100,100);
			profile.setImage(bytes);
			profileRepository.saveAndFlush(profile);
			/* ##########End Saving the File ################ */
			return new ResponseEntity<String>(profile.getImage(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}

	}
	/**
     * This function resize the image file and returns the BufferedImage object that can be saved to file system.
	 * @throws IOException 
     */
    public byte[] resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        byte[] imageInByte = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try {
			ImageIO.write( bufferedImage, "png", baos );
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    		
        return imageInByte;
    }
	@Override
	public ResponseEntity<Object> saveImage(MultipartFile uploadfile, User user, Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

}
