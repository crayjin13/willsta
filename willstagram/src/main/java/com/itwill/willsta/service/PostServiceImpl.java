package com.itwill.willsta.service;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.willsta.domain.Likes;
import com.itwill.willsta.domain.Post;
import com.itwill.willsta.domain.PostImage;
import com.itwill.willsta.repository.PostDao;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostDao postDao;
	
	String uploadFolder = "C:\\eclipse-workspace5\\willsta\\willsta\\willstagram\\src\\main\\webapp\\contents\\post_contents";
	@Override
	public Post createPost(Post post, MultipartFile[] uploadFile) {
		/*
		 * 1. 포스트 생성
		 * 2. 생성된 번호로 파일이름 설정
		 * 3. 컨텐츠정보생성
		 * 4. 파일저장
		 */
		
		Post postOne=null;
		int rn = postDao.insert(post);
		if(rn >0) {
			//2.파일처리(파일네임은DB에 저장 하고 파일은 image폴더에 저장)
			String filename="";
			String filterFileName="";
			PostImage pi;
			System.out.println("##########"+uploadFile.length);
			for (MultipartFile multipartFile : uploadFile) {
	
					filename = multipartFile.getOriginalFilename();
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					String sepString = filename.substring(filename.lastIndexOf("."), filename.length());
					String maxContentNo = postDao.maxContentNo(post.getpNo());
					if(filename.toUpperCase().endsWith(".MP4") || filename.endsWith(".AVI") || filename.endsWith(".MKV") || filename.endsWith(".MOV")) {
						filterFileName =  "mov_";
					}else if(filename.toUpperCase().endsWith(".JPG") || filename.endsWith(".PNG") || filename.endsWith(".JPEG") || filename.endsWith(".GIF")) {
						filterFileName =  "img_";
					} else {
						filterFileName =  "etc_";
					}
					
					filterFileName+=post.getpNo()+"_"+maxContentNo.trim()+sepString;	
					
						System.out.println("$$$$$$$$$$$$$$"+filterFileName);
					if (!(filename == null || filename.equals(""))) {
						pi = new PostImage(post.getpNo(), filterFileName);	
						postDao.insertImg(pi);
						File saveFile = new File(uploadFolder, filterFileName);
						try {
							multipartFile.transferTo(saveFile);
						} catch (Exception e) {
							e.getMessage();
						}
					}
			}
		
		
			//성공인 경우 해당 포스트를 전송해 줌 1개의 포스트를  jsp 로 구성해서 던져줌.
			postOne = postDao.selectPost(post.getpNo(), post.getmId());
			postOne.setTagArray(postOne.getHasTag().split(" "));
		}
		
		return postOne;
	}
	

	@Override
	public int modifyPost(Post post) {
		/*
		 * 1.내용수정
		 * 2.컨텐츠의 추가 또는 삭제[이건 보류]
		 */
		return postDao.update(post);
	}

	@Override
	public int removePost(Integer pNo) {
		/*
		 * 1. 컨텐츠 리스트 조회
		 * 2. 파일삭제
		 * 3. post삭제
		 */
		File removeFile;
		List<PostImage> postImageList = postDao.selectContents(pNo);
		for (PostImage postImage : postImageList) {
			removeFile= new File(uploadFolder, postImage.getFileName());
			removeFile.delete();
		}
		
		return postDao.delete(pNo);
	}

	@Override
	public List<Post> selectMyList(String userId) {
		return postDao.selectMyList(userId);
	}

	@Override
	public Post selectPost(Integer pNo, String mId) {
		//이거 호출 시 뷰카운트 업데이트 해줌
		postDao.up_viewcount(pNo);
		return postDao.selectPost(pNo, mId);
	}

	@Override
	public List<PostImage> selectContents(Integer pNo) {
		return postDao.selectContents(pNo);
	}


	@Override
	public String insert_like(Likes lk) {
		int rn = postDao.select_like_count(lk);
		if(rn==0) {
			postDao.insert_like(lk);
			return "insert";
		} else {
			postDao.delete_like(lk);
			return "delete";
		}
	}
	
	@Override
	public int status_change(Integer pNo, String status) {

		return postDao.status_update(pNo, status);
	}

	

}
