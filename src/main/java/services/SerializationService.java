package services;

import com.xebia.protobuf.proto.GpsLocationProtos;
import com.xebia.protobuf.proto.GroundDataProtos;
import com.xebia.protobuf.proto.SensorDataProtos;
import com.xebia.protobuf.proto.SensorDataProtos.SensorData.SensorType;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class SerializationService {

    public static void main(String[] args) throws Exception {
        GroundDataProtos.GroundData groundData = buildMsg();
        System.out.println(groundData.getSerializedSize());
        OutputStream outputStream = new FileOutputStream(".\\src\\main\\resources\\result");
        groundData.writeTo(outputStream);
    }

    private static GroundDataProtos.GroundData buildMsg() {
        GpsLocationProtos.GpsLocation.Builder gpsLocationBuilder = GpsLocationProtos.GpsLocation.newBuilder()
                .setLatitude(488679)
                .setLongitude(240093);

        SensorDataProtos.SensorData sensorHumidity = SensorDataProtos.SensorData.newBuilder()
                .setSensorType(SensorType.HUMIDITY)
                .setValue(35)
                .build();

        SensorDataProtos.SensorData sensorTemperature = SensorDataProtos.SensorData.newBuilder()
                .setSensorType(SensorType.TEMPERATURE)
                .setValue(14)
                .build();

        return GroundDataProtos.GroundData.newBuilder()
                .setId(1)
                .setTimestamp(1570402900)
                .setGpsLocation(gpsLocationBuilder)
                .addSensorDatas(0, sensorHumidity)
                .addSensorDatas(1, sensorTemperature)
                .build();
    }
}
