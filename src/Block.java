import java.util.*;
import java.security.*;
import java.nio.charset.*;

public class Block
{
	private Block last;
	private String hash;
	private String lastHash;
	private String data;
	private Long timestamp;

	public Block(String data)
	{
		Calendar now = Calendar.getInstance();
		this.setTimestamp(now.getTimeInMillis());
		this.setLastHash("");
		this.setData(data);
		this.setHash(this.hash(this.getData()));
	}

	public Block(Block last, String data)
	{
		this.setLastBlock(last);
		this.setLastHash(last.getHash());
		this.setData(data);
		this.setHash(this.hash(this.getData()));
	}

	private void setHash(String hash)
	{
		this.hash = hash;
	}

	public String getHash()
	{
		return hash;
	}

	public void setLastHash(String lastHash)
	{
		this.lastHash = lastHash;
	}

	public String getLastHash()
	{
		return lastHash;
	}
	
	private void setLastBlock(Block last)
	{
		this.last = last;
	}
	
	public Block getLastBlock()
	{
		return last;
	}

	private void setData(String data)
	{
		this.data = data;
	}

	public String getData()
	{
		return data;
	}

	private String hash(String data)
	{
		data += "-{{ts}}-" + this.getTimestamp();
		String hashed = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashByte = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			StringBuffer hashString = new StringBuffer();
			for (int i = 0; i < hashByte.length; i++) {
				String hex = Integer.toHexString(0xff & hashByte[i]);
				if (hex.length() == 1) {
					hashString.append('0');
				}
				hashString.append(hex);
			}
			hashed = hashString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashed;
	}

	public boolean validate(Block target)
	{
		System.out.println("Validating...");
		String currentHash = this.getHash();
		String targetLastHash = target.getLastBlock().getHash();
		System.out.println("Hash of this block: " + currentHash);
		System.out.println("Hash of target block: " + targetLastHash);
		boolean isValid = currentHash.equals(targetLastHash);
		return isValid;
	}
	
	private void setTimestamp(Long timestamp)
	{
		this.timestamp = timestamp;
	}
	
	public Long getTimestamp()
	{
		return this.timestamp;
	}
}
