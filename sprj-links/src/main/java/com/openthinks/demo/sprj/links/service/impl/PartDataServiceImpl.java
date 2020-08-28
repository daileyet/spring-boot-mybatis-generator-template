/**
 * 
 */
package com.openthinks.demo.sprj.links.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.openthinks.demo.sprj.links.core.JSONPartPoolManagerPoxy;
import com.openthinks.demo.sprj.links.core.msg.WholeChannelStringMessage;
import com.openthinks.demo.sprj.links.core.msg.WholeMessage;
import com.openthinks.demo.sprj.links.core.parts.PartType;
import com.openthinks.demo.sprj.links.core.parts.Whole;
import com.openthinks.demo.sprj.links.service.PartDataService;

import io.netty.channel.ChannelId;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Component
public class PartDataServiceImpl implements PartDataService {
	private final static Logger LOGGER = LoggerFactory.getLogger(PartDataServiceImpl.class);
	private final JSONPartPoolManagerPoxy<ChannelId> channelDataPool = new JSONPartPoolManagerChannelPoxy();

	@Override
	public void put(ChannelId channelId, String content) {
		channelDataPool.put(channelId, content);
	}

	@Override
	public void remove(ChannelId channelId) {
		channelDataPool.remove(channelId);
	}

	/**
	 * Use {@link ChannelId} as part data reference key
	 * 
	 * @author dailey.dai@openthinks.com
	 *
	 */
	private final class JSONPartPoolManagerChannelPoxy extends JSONPartPoolManagerPoxy<ChannelId> {

		@Override
		protected void processWholeData(Whole<ChannelId, String> data, PartType type) {
			WholeMessage<ChannelId, String> message = new WholeChannelStringMessage(data);
			// TODO process message
			LOGGER.debug("Received JSON msg:{}",message);
		}

	}
}
