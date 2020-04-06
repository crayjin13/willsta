<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="comment-sec">
	<ul>
		<li>
			<div class="comment-list">
				<div class="bg-img">
					<img src="images/resources/bg-img1.png" alt="">
				</div>
				<div class="comment">
					<h3>John Doe</h3>
					<span><img src="images/clock.png" alt=""> 3 min ago</span>
					<p>Lorem ipsum dolor sit amet,</p>
					<a href="#" title="" class="active"><i class="fa fa-reply-all"></i>Reply</a>
				</div>
			</div> <!--comment-list end-->
			<ul>
				<li>
					<div class="comment-list">
						<div class="bg-img">
							<img src="images/resources/bg-img2.png" alt="">
						</div>
						<div class="comment">
							<h3>John Doe</h3>
							<span><img src="images/clock.png" alt=""> 3 min ago</span>
							<p>Hi John</p>
							<a href="#" title=""><i class="fa fa-reply-all"></i>Reply</a>
						</div>
					</div> <!--comment-list end-->
				</li>
			</ul>
		</li>
		<li>
			<div class="comment-list">
				<div class="bg-img">
					<img src="images/resources/bg-img3.png" alt="">
				</div>
				<div class="comment">
					<h3>John Doe</h3>
					<span><img src="images/clock.png" alt=""> 3 min ago</span>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Aliquam luctus hendrerit metus, ut ullamcorper quam finibus at.</p>
					<a href="#" title=""><i class="fa fa-reply-all"></i>Reply</a>
				</div>
			</div> <!--comment-list end-->
		</li>
	</ul>
</div>
<!--comment-sec end-->

<!-- post-comment -->
<div class="post-comment" post_no="${post.pNo}">
	<div class="cm_img">
		<img src="images/resources/bg-img4.png" alt="">
	</div>
	<div class="comment_box">
		<form class="comments_insert_form">
			<!-- 댓글 내용 쓰는 곳 -->
			<input type="text" placeholder="Post a comment" name="cContents"
				class="cContents"> <input type="hidden" name="pNo"
				value="${post.pNo}">
			<button type="button" class="comments_insert_button">Send</button>
		</form>
	</div>
</div>
<!--post-comment end-->