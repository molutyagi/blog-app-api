package com.blog.app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.FileService;
import com.blog.app.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;

	@Value("profile.image")
	private String path;

//	POST - Create user
	@PostMapping("add")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createdUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

//	PUT - Update user
	@PutMapping("update{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {

		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}

//	DELETE - Delete user

	@DeleteMapping("delete{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {

		this.userService.deleteUser(userId);

		return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}

//	GET - Fetch user

//	Get all users
	@GetMapping("getall")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return ResponseEntity.ok(this.userService.getAllUsers());
	}

//	Get specified user
	@GetMapping("user{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

//	Post image
	@PostMapping("user{userId}/image")
	public ResponseEntity<UserDto> imageUpload(@PathVariable Integer userId, @RequestParam("image") MultipartFile image)
			throws IOException {

		UserDto userDto = this.userService.getUserById(userId);
		String fileName = this.fileService.uploadImage(path, image);
		userDto.setProfile_img(fileName);

		return ResponseEntity.ok(this.userService.updateUser(userDto, userId));
	}

//	Get Image
	@GetMapping(value = "user{userId}/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable Integer userId, @PathVariable String imageName, HttpServletResponse res)
			throws IOException {
		InputStream is = this.fileService.getResource(path, imageName);
		res.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(is, res.getOutputStream());
	}
}
