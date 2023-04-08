package com.shuishu.demo.multiple.db.entity.po;


import com.shuishu.demo.multiple.db.common.domain.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 13:03
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源
 * <p></p>
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_data_source_info")
@Comment("数据源表")
public class DataSourceInfo extends BasePO {
    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.demo.multiple.db.common.id.CustomIdGenerator")
    @Comment("数据源id")
    private Long dataSourceId;

    @Comment("数据源code（客户的标识）")
    private String code;

    @Comment("数据源名称（租户名、客户名、公司名）")
    private String name;

    @Comment("数据源类型")
    private String type;

    @Comment("数据库连接")
    private String jdbcUrl;

    @Comment("数据源用户名")
    private String userName;

    @Comment("数据源密码")
    private String passWord;

    @Comment("数据源驱动")
    private String driverClassName;

    @Comment("最大连接数，包括空闲连接和正在使用的连接")
    private Integer maximumPoolSize;

    @Comment("最少连接数，包括空闲连接和正在使用的连接")
    private Integer minimumIdle;

    @Comment("连接在池中空闲的最大时间(以毫秒为单位)，最大变化量为+30秒，平均变化量为+15秒")
    private Long idleTimeout;

    @Comment("空闲状态连接的最大生存期")
    private Long maxLifetime;

    @Comment("连接超时时间")
    private Long connectionTimeout;

    @Comment("数据源描述")
    private String description;

    @Comment("是否启用")
    private Boolean enable;

}
