package com.honeysense.magpie.framework.object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "实例 - 基础")
@Getter
@Setter
@MappedSuperclass
@Configuration
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updatedAt"}, allowGetters = true)
public class MagpieEntity extends MagpieObject {
    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MagpieIdentifierGenerator")
    @GenericGenerator(name = "MagpieIdentifierGenerator", strategy = "com.honeysense.magpie.framework.spring.uuid.MagpieIdentifierGenerator")
    protected Long id;

    @ApiModelProperty(value = "创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @ApiModelProperty(value = "上次更新时间")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;
}
