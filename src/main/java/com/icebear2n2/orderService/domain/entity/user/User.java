package com.icebear2n2.orderService.domain.entity.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 엔티티")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "사용자 이름", example = "test")
    private String username;

    @Column(unique = true)
    @Schema(description = "사용자 이메일", example = "test@example.com")
    private String email;

    @Schema(description = "소셜 로그인 제공자", example = "kakao")
    private String provider;

    @Schema(description = "제공자의 사용자 ID", example = "1234567890")
    private String providerUserId;

    @Schema(description = "사용자 비밀번호")
    private String password;

    @Schema(description = "사용자 전화번호", example = "010-1234-5678")
    private String userPhone;

    @Schema(description = "리프레시 토큰")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Schema(description = "사용자 역할", example = "USER")
    private Role role;

    @CreationTimestamp
    @Schema(description = "계정 생성 시간")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Schema(description = "계정 최종 수정 시간")
    private Timestamp updatedAt;

    @Schema(description = "계정 삭제 시간")
    private Timestamp deletedAt;

}
