package study.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.coobird.thumbnailator.Thumbnailator;
import study.spring.dto.AttachFileDTO;

@Controller
@RequestMapping("/upload/*")
public class UploadController {
	private static final Logger log = LoggerFactory.getLogger(UploadController.class);
	@GetMapping("/upload")
	public void upload() {}
	@PostMapping("/upload")
	public ModelAndView upload(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list= new ArrayList<AttachFileDTO>();
		String uploadFolder ="C:\\Users\\USER\\Desktop\\temp";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date= new Date();
		String str=sdf.format(date);
		str.replace("-", File.separator);
		File uploadPath=new File(uploadFolder, str);
		if(!uploadPath.exists())uploadPath.mkdirs();
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachFileDTO= new AttachFileDTO();
			String uploadFileName=multipartFile.getOriginalFilename();
			uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			attachFileDTO.setFileName(uploadFileName);
			UUID uuid=UUID.randomUUID();
			attachFileDTO.setUuid(uuid.toString());
			attachFileDTO.setUploadPath(uploadPath.toPath().toString());
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			attachFileDTO.setEncodePath(uploadPath.toString(), uploadFileName);
			File saveFile = new File(uploadPath,uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
				attachFileDTO.setImage(Files.probeContentType(saveFile.toPath()).startsWith("image"));
				if(attachFileDTO.isImage()) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				list.add(attachFileDTO);
			}catch(Exception e) {log.error(e.getMessage());}
			
		}
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("upload/upload");
		modelAndView.addObject("uploadList", list);
		return modelAndView;
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(@RequestParam("fileName") String fileName){
		ResponseEntity<byte[]> result = null;
		String displayPath=fileName.substring(0, fileName.lastIndexOf("\\"));
		String displayFileName="\\s_"+fileName.substring(fileName.lastIndexOf("\\")+1);
		try {
			File file= new File(displayPath+displayFileName);
			HttpHeaders httpHeaders= new HttpHeaders();
			httpHeaders.add("Content-Type", Files.probeContentType(file.toPath()));
			result= new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),httpHeaders,HttpStatus.OK);
		}catch(Exception e) {e.printStackTrace();}
		return result;
	}
	@GetMapping("/bigPicture")
	public ResponseEntity<byte[]> bigPicture(@RequestParam("fileName") String fileName){
		ResponseEntity<byte[]> result = null;
		try {
			File file= new File(fileName);
			HttpHeaders httpHeaders= new HttpHeaders();
			httpHeaders.add("Content-Type", Files.probeContentType(file.toPath()));
			result= new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),httpHeaders,HttpStatus.OK);
		}catch(Exception e) {e.printStackTrace();}
		return result;
	}
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName,@RequestHeader("User-Agent") String userAgent){
		Resource resource = new FileSystemResource(fileName);
		if(resource.exists()==false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String resourceName= resource.getFilename();
		resourceName=resourceName.substring(resourceName.indexOf("_")+1);
		HttpHeaders headers= new HttpHeaders();
		try {
			if(userAgent.contains("Edge")) {
				resourceName=URLEncoder.encode(resourceName,"UTF-8");
			}
			if(userAgent.contains("Chrome browser"));{
				resourceName=new String(resourceName.getBytes("UTF-8"),"ISO-8859-1");
			}
			headers.add("Content-Disposition", "attachment; filename="+ resourceName);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
}
