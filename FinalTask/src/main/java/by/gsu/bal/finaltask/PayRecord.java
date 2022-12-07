package by.gsu.bal.finaltask;

import java.util.Date;

public class PayRecord {
  private long id;
  private String serviceName;
  private int serviceId;
  private Date payDate;
  private float value;
  private String unit;
  private float cost;

  public PayRecord() {
  }

  public PayRecord(long id, String serviceName, int serviceId, Date payDate, float value, String unit, float cost) {
    this.id = id;
    this.serviceName = serviceName;
    this.serviceId = serviceId;
    this.payDate = payDate;
    this.value = value;
    this.unit = unit;
    this.cost = cost;
  }

  public PayRecord(String id, String serviceName, String serviceId, Date payDate, String value, String unit) {
    this.id = Long.parseLong(id);
    this.serviceId = Integer.parseInt(serviceId);
    this.serviceName = serviceName;
    this.payDate = payDate;
    this.value = Float.parseFloat(value);
    this.unit = unit;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getServiceId() {
    return serviceId;
  }

  public void setServiceId(int serviceId) {
    this.serviceId = serviceId;
  }

  public Date getPayDate() {
    return payDate;
  }


  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  @Override
  public String toString() {
    return "PayRecord{" +
        "id=" + id +
        ", serviceName='" + serviceName + '\'' +
        ", serviceId=" + serviceId +
        ", payDate=" + payDate +
        ", value=" + value +
        ", unit='" + unit + '\'' +
        '}';
  }
}
