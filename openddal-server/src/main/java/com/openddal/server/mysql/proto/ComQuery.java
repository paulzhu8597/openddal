package com.openddal.server.mysql.proto;

import java.util.ArrayList;

public class ComQuery extends Packet {
    public String query = "";
    
    public ArrayList<byte[]> getPayload() {
        ArrayList<byte[]> payload = new ArrayList<byte[]>();
        
        payload.add(Proto.build_byte(Flags.COM_QUERY));
        payload.add(Proto.build_eop_str(this.query));
        
        return payload;
    }

    public static ComQuery loadFromPacket(byte[] packet) {
        ComQuery obj = new ComQuery();
        Proto proto = new Proto(packet, 3);
        
        obj.sequenceId = proto.get_fixed_int(1);
        proto.get_filler(1);
        obj.query = proto.get_eop_str();
        
        return obj;
    }

    @Override
    public String toString() {
        return "COM_QUERY[query=" + query + "]";
    }
    
}
