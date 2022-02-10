package com.ssafy.togetherhomt.user;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
@Builder
public class UserDto {

    @ApiParam(value = "사용자 계정 주소")
    @NotNull
    @Size(max = 50)
    private String email;

    @ApiParam(value = "사용자 계정 별명(nickname)")
    @NotNull
    @Size(min = 3, max = 10)
    private String username;

    @ApiParam(value = "사용자 계정 소개")
    private String introduce;

    @ApiParam(value = "사용자 계정 프로필 사진 경로")
    private String imagePath;

    @ApiParam(value = "사용자 계정을 팔로우하는 사람 수")
    private Long cntFollower;
    private List<UserDto> followers;
    @ApiParam(value = "사용자 계정이 팔로우하는 사람 수")
    private Long cntFollowing;
    private List<UserDto> followings;

}